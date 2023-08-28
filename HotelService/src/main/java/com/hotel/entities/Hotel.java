package com.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hotels")
public class Hotel {
	@Id
   private String hotelId;
   private String name;
   private String location;
   private String about;
public String getHotelId() {
	return hotelId;
}
@Override
public String toString() {
	return "Hotel [hotelId=" + hotelId + ", name=" + name + ", location=" + location + ", about=" + about + "]";
}
public void setHotelId(String hotelId) {
	this.hotelId = hotelId;
}
public Hotel(String hotelId, String name, String location, String about) {
	super();
	this.hotelId = hotelId;
	this.name = name;
	this.location = location;
	this.about = about;
}
public Hotel() {
	super();
	// TODO Auto-generated constructor stub
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getAbout() {
	return about;
}
public void setAbout(String about) {
	this.about = about;
}
}
