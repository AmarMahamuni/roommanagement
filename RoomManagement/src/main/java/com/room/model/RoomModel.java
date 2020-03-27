package com.room.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="room")
@SequenceGenerator(name="ROOM_SEQ", sequenceName = "room_seq")
public class RoomModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO ,generator="ROOM_SEQ")
	long id;
	
	   @Column
	   String room_name;
	
     
	
     @OneToMany(mappedBy="room" ,cascade = {CascadeType.MERGE})
     @JsonManagedReference
     private List<UserModel> users=new ArrayList<UserModel>();

	
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
	@MapsId
	private RoomAddressModel room_address;



	public List<UserModel> getUsers() {

		return users;
	}




	public void setUsers(List<UserModel> users) {
		this.users = users;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getRoom_name() {
		return room_name;
	}




	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}




	public RoomAddressModel getRoom_address() {
		return room_address;
	}




	public void setRoom_address(RoomAddressModel room_address) {
		this.room_address = room_address;
	}




	public RoomModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RoomModel( String room_name, List<UserModel> users, RoomAddressModel room_address) {
		super();
		this.room_name = room_name;
		this.users = users;
		this.room_address = room_address;
	}




	@Override
	public String toString() {
		return "RoomModel [id=" + id + ", room_name=" + room_name + ", room_address=" + room_address + "]";
	}

	
	
	
}
