package com.xadmin.parcelsystem.bean;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Parcel {
	
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime localDateTime = LocalDateTime.now();
	
	private int id;
	private String name;
	private String noPhone;
	private String noMatric;
	private String dateIn = localDateTime.format(formatter);
	private String dateOut = localDateTime.format(formatter);
	


	
	
	public Parcel(String name, String noPhone, String noMatric, String dateIn, String dateout) {
		super();
		this.name = name;
		this.noPhone = noPhone;
		this.noMatric = noMatric;
		this.dateIn = dateIn;
		this.dateOut = dateout;
	}
	public Parcel(int id, String name, String noPhone, String noMatric, String dateIn, String dateout) {
		super();
		this.id = id;
		this.name = name;
		this.noPhone = noPhone;
		this.noMatric = noMatric;
		this.dateIn = dateIn;
		this.dateOut = dateout;
	}
	
	public Parcel(int id, String name, String noPhone, String noMatric, String dateIn) {
		super();
		this.id = id;
		this.name = name;
		this.noPhone = noPhone;
		this.noMatric = noMatric;
		this.dateIn = dateIn;
	}
	
	
	public Parcel(String name, String noPhone, String noMatric) {
		super();
		this.name = name;
		this.noPhone = noPhone;
		this.noMatric = noMatric;
	}
	
	public Parcel(int id, String name, String noPhone, String noMatric) {
		super();
		this.id = id;
		this.name = name;
		this.noPhone = noPhone;
		this.noMatric = noMatric;
	}
	public String getDateIn() {
	    return dateIn;
	}
	public String getDateOut() {
		return dateOut;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNoPhone() {
		return noPhone;
	}
	public void setNoPhone(String noPhone) {
		this.noPhone = noPhone;
	}
	public String getNoMatric() {
		return noMatric;
	}
	public void setNoMatric(String noMatric) {
		this.noMatric = noMatric;
	}
	

}
