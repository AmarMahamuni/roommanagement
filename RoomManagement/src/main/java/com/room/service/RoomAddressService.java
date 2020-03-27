package com.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.room.Repo.RoomAddressJpaRepo;
import com.room.model.RoomAddressModel;
import com.room.model.RoomModel;
import com.room.model.UserModel;

@Service
public class RoomAddressService {

	@Autowired
	private RoomAddressJpaRepo room_address_repo;
	
	@Autowired
	private UserService user_service;

	
	public void save(RoomAddressModel room_address)
	{
		room_address_repo.save(room_address);
	}
	
	public ResponseEntity<RoomAddressModel> show()
	{
		if( user_service.loggedUser().getRoom() != null) {
			return new ResponseEntity<RoomAddressModel>(user_service.loggedUser().getRoom().getRoom_address(), HttpStatus.OK);	
		}
		else 
			return new ResponseEntity(HttpStatus.NO_CONTENT);	
	
	}
	
	
	public ResponseEntity<RoomAddressModel> updateRoomAddress(RoomAddressModel updatedaddress)
	{
		if( user_service.loggedUser().getRoom() != null) {
			
			return new ResponseEntity<RoomAddressModel>(room_address_repo.save(updatedaddress), HttpStatus.OK);	
		
		}
		else 
			return new ResponseEntity(HttpStatus.NO_CONTENT);	
	
	}
	
}
