package com.user.dto;

public class HotelDto {
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
public HotelDto(String hotelId, String name, String location, String about) {
	super();
	this.hotelId = hotelId;
	this.name = name;
	this.location = location;
	this.about = about;
}
public HotelDto() {
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
