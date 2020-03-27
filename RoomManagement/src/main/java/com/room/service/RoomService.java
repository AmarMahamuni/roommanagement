package com.room.service;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.room.CustomErrorType;
import com.room.Repo.RoomJPARepo;
import com.room.Repo.UserJpaRepo;
import com.room.model.RoomModel;
import com.room.model.UserModel;



@Service
public class RoomService {
	
	@Autowired
	private RoomJPARepo room_repo;

	//@Autowired
	//HttpSession session;
	
	@Autowired
	private UserJpaRepo user_repo;
	
	@Autowired
	private UserService user_service;
	
	List<UserModel> list=new ArrayList<UserModel>();
	
	public ResponseEntity<String> createRoom(RoomModel room) throws Exception
	{
		try {
			System.out.println("service"+ room);
			UserModel user=user_service.loggedUser();
			System.out.println("user"+user);
			list.add(user);
			room.setUsers(list);
			System.out.println("list123"+room.getUsers());
			System.out.println("list");
			list.forEach((use)->System.out.println(use));
			user.setIsadmin(true);
			System.out.println("is"+room);
			user.setRoom(room);
			
				room_repo.save(room);
				user_repo.save(user);
				list.remove(user);

		}
		catch (Exception e) {
			System.out.println("catch");
			throw new Exception("Creating Room Error", e);
		}
		System.out.println("rrr"+room.getUsers());
		System.out.println("lllll");
		list.forEach((use)->System.out.println(use));
		return new ResponseEntity<String>( HttpStatus.CREATED);

	}
  
	public ResponseEntity joinRoom(long roomid)
	{
		System.out.println("joinroom");
		RoomModel room=room_repo.findById(roomid).orElse(null);
		if(room==null)
		{
			System.out.println("join rooom null");
			 return new ResponseEntity(new CustomErrorType("Room is not created or deleted " 
	                  ), HttpStatus.NOT_FOUND);
		}
		UserModel user=user_service.loggedUser();
		if(user==null)
		{
			System.out.println("join user null");
			 return new ResponseEntity(new CustomErrorType("Please Login with your account to Join room " 
	                  ), HttpStatus.NOT_FOUND);
		}
		if(user.getRoom()!=null) {
			System.out.println("join rooom is already there");

			return new ResponseEntity(new CustomErrorType("User is already present in the other ROOM" 
	                  ), HttpStatus.CONFLICT);	
		}
		//System.out.println("user"+user);
		list.add(user);
		room.setUsers(list);
		user.setRoom(room);
		room_repo.save(room);
		user_repo.save(user);	
		list.remove(user);
		return new ResponseEntity(new CustomErrorType("User is joined the ROOM" 
                ), HttpStatus.OK);	

	}

	
	
public UserModel roomUsers() {
	UserModel user=user_service.loggedUser();
		//System.out.println(  "session"+session.getAttribute("logedInUser"));
		System.out.println("userseee");
           System.out.println(user);
          // return user;
           System.out.println("ur"+user.getRoom().getId());
      System.out.println(user_repo.findByRoom(user.getRoom()));   
      
 //     UserModel user1=  user_repo.findByRoom(user.getRoom().getId());
  //    System.out.println("uuu"+user1);
	return null;      
	}
	
	public List<RoomModel> show() {
		
	return	room_repo.findAll();
		
	}
	

}
