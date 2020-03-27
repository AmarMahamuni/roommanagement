package com.room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.room.model.RoomAddressModel;
import com.room.service.RoomAddressService;

@RestController
@RequestMapping("/room-address")
public class RoomAddressController {

	@Autowired
	private RoomAddressService roomaddress_service;
	
	@GetMapping("/show")
	public ResponseEntity<RoomAddressModel> showRoomAddress()
	{
		//System.out.println("show"+roomaddress_service.show());
	    return roomaddress_service.show();
	}

	@PutMapping("/update")
	public ResponseEntity<RoomAddressModel> updateRoomAddress(@RequestHeader RoomAddressModel roomaddress)
	{
		//System.out.println("show"+roomaddress_service.show());
	    return roomaddress_service.updateRoomAddress(roomaddress);
	}
	
}
