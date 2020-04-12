package com.room.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.room.model.RoomModel;
import com.room.model.UserModel;
import com.room.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService room_service;
	
	@PostMapping("/create")
	public ResponseEntity create(@RequestBody RoomModel roomdetails) throws Exception
	{
		return room_service.createRoom(roomdetails);
	}
	
	
@PostMapping("/joinroom")
	public ResponseEntity joinroom(@RequestHeader long roomid) {
		
             return room_service.joinRoom(roomid);	
	}
	
	
	
	@GetMapping("/show")
	public ResponseEntity show()
	{
	   List<RoomModel> rooms=room_service.show();
	       
		 if (rooms.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<RoomModel>>(rooms, HttpStatus.OK);       
	}
	
	
	@GetMapping("/roomusers")
	public UserModel roomusers(){
		System.out.println("roomco");
		return room_service.roomUsers();
	}
	
/*	@GetMapping("/show/{id}")
	public RoomModel showroom(@PathVariable ("id") long id)
	{
		return room_service.show(id);
	}
	*/
}
