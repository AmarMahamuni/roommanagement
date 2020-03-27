package com.room.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "user_seq")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	long id;

	@Column
	String user_name;

	@Column
	private boolean enabled;

	@Column
	String age;

	@Column(nullable = false)
	String email;

	@Column(nullable = false)
	String phone_number;

	@Column
	String password;

	@Column
	boolean isadmin;

	@ManyToOne
	@JoinColumn(name = "room")
	@JsonBackReference
	private RoomModel room;

	public UserModel(String user_name, boolean enabled, String age, String email, String phone_number, String password,
			boolean isadmin, RoomModel room) {
		super();
		this.user_name = user_name;
		this.enabled = enabled;
		this.age = age;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.isadmin = isadmin;
		this.room = room;
	}

	public RoomModel getRoom() {
		return room;
	}

	public void setRoom(RoomModel room) {
		this.room = room;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		
		public String getUser_name() {
			return user_name;
		}
		
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		
		public String getAge() {
			return age;
		}
		
		public void setAge(String age) {
			this.age = age;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		@Override
		public String toString() {
			return "UserModel [id=" + id + ", user_name=" + user_name + ", age=" + age + ", email=" + email + ", password="
					+ password + "]";
		}
		
		


}
