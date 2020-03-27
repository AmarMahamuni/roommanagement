package com.room.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.room.emailverification.ConfirmationToken;
import com.room.model.UserModel;
import com.room.security.AuthenticationResponse;
import com.room.security.JwtUtil;
import com.room.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "UserRestController", description = "REST Apis related to UserModel Entity!!!!")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService user_service;

	@Autowired
	HttpSession session;

	// @Autowired
	// private MyUserDetailsService userDetailsService;
	@ApiOperation(value = "Create Account", notes = "Registraion of the user")
	@PostMapping("/create-account")
	public ResponseEntity create(@RequestBody UserModel user) throws AddressException, MessagingException, IOException {
		// System.out.println("create con");
		return user_service.createUser(user);
	}

	@RequestMapping(value = "/confirm-account")
	public ResponseEntity confirmUserAccount(@RequestParam("token") String confirmationToken) {
		return user_service.confirmUserAccount(confirmationToken);

	}

	@GetMapping("/show")
	public String show1() {
		System.out.println("showwww.....");
		return "hello";
	}

	
	@GetMapping("/me")
	@ApiOperation(value = "Show curent logged user", notes = "Show curent logged user", response = UserModel.class)
	public UserModel show() {
		return user_service.loggedUser();
	}

	@ApiOperation(value = "Login", notes = "Login", response = AuthenticationResponse.class)
	@GetMapping("/authenticate")
	public ResponseEntity<?> loginByEmail1(@RequestHeader String email, @RequestHeader String password)
			throws Exception {

		// System.out.println("email" + email + "pass" + password);
		// System.out.println();
		return user_service.loginByEmail(email, password);
	}

}
