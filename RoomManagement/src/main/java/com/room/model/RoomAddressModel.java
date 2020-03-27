package com.room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="roomaddressmodel")
@SequenceGenerator(name="RoomAddressModel_seq" ,sequenceName = "roomaddressmodel_seq")
public class RoomAddressModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO , generator = "RoomAddressModel_seq")
	long id;
	
	@Column
	String room_number;
	
	@Column
	String area;
	
	@Column
	String city;
	
	@Column
	String state;
	
	@Column
	int pincode;

	public RoomAddressModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomAddressModel(String room_number, String area, String city, String state, int pincode) {
		super();
		this.room_number = room_number;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoom_number() {
		return room_number;
	}

	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "RoomAddressModel [id=" + id + ", room_number=" + room_number + ", area=" + area + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + "]";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
	
	
	
	
}
