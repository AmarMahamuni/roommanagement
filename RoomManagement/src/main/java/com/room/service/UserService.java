package com.room.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.room.CustomErrorType;
import com.room.Repo.UserJpaRepo;
import com.room.emailverification.ConfirmationToken;
import com.room.emailverification.ConfirmationTokenRepository;
import com.room.model.UserModel;
import com.room.security.AuthenticationResponse;
import com.room.security.JwtUtil;
import com.room.util.EmailMessage;
import com.room.util.SendEmail;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserJpaRepo user_repo;

	@Autowired
	private AuthenticationManager authenticationmanager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private ConfirmationTokenRepository Token_repository;

	@Autowired
	private EmailMessage email_message;

	@Autowired
	private SendEmail send_email;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Create User Service
	public ResponseEntity createUser(UserModel user) throws AddressException, MessagingException, IOException {
		UserModel usermodel = user_repo.findByEmail(user.getEmail()).orElse(null);
		if (usermodel != null) {
			if (!usermodel.isEnabled()) {
				return new ResponseEntity(
						new CustomErrorType(
								"User is already Created account with email: " + user.getEmail() + "But not activated"),
						HttpStatus.CONFLICT);
			}
			return new ResponseEntity(new CustomErrorType("User is already have account with email " + user.getEmail()),
					HttpStatus.CONFLICT);
		}
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user_repo.save(user);
		// Generating Token for Email verification
		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		Token_repository.save(confirmationToken);
		// System.out.println("send mail");
		email_message.setTo_address(user.getEmail());
		email_message.setSubject("RegistrationTesting......");
		email_message.setBody("To confirm your account, please click here : "
				+ "http://localhost:8080/user/confirm-account?token=" + confirmationToken.getConfirmationToken());
		send_email.sendmail(email_message);
		System.out.println("tt" + confirmationToken.getConfirmationToken());
		return new ResponseEntity(("Verification Link sent to Email: "+user.getEmail()+" please verify to active your account"), HttpStatus.OK);
	}

	// Email verification
	public ResponseEntity<String> confirmUserAccount(String confirmationToken) {
		ConfirmationToken token = Token_repository.findByConfirmationToken(confirmationToken);
		System.out.println("token" + token);
		if (token != null) {
			UserModel user = user_repo.findByEmail(token.getUser().getEmail()).orElse(null);
			System.out.println("uuser" + user);
			user.setEnabled(true);
			user_repo.save(user);
			return new ResponseEntity(("User account: " + user.getEmail() + " is  activated "), HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
	}

	// User Login Service
	public ResponseEntity<?> loginByEmail(String email, String password) throws Exception {
		try {
			System.out.println("tryyy");
			authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (BadCredentialsException e) {
			System.out.println("catch");
			throw new Exception("Incorrect username or password", e);
		}
		UserModel user = getUserByEmail(email);
		if (user.isEnabled()) {
			final UserDetails userDetails = loadUserByUsername(email);
			// System.out.println("uu" + userDetails);
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
		}
		return new ResponseEntity(new CustomErrorType("User account: " + user.getEmail() + "is not yet activated "),
				HttpStatus.PARTIAL_CONTENT);
	}

	// Getting User By Email
	public UserModel getUserByEmail(String email) {
		// System.out.println("loginser");
		return user_repo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with Email: " + email));
	}

	// Gettting Current Looged User
	public UserModel loggedUser() {
		String logged_email = SecurityContextHolder.getContext().getAuthentication().getName();
		// Object name = auth.getPrincipal().;
		System.out.println("nameeeeee " + logged_email);
		return user_repo.findByEmail(logged_email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with Email: " + logged_email));
		// return logged_email;
	}

	// Getting All users
	public ResponseEntity getAllUsers() {
		List<UserModel> users = user_repo.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user = getUserByEmail(email);
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());

	}

}
