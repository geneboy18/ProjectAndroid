package com.example.person;

import android.graphics.Bitmap;
import android.net.Uri;

public class Person {
	private Bitmap img;
	private String name,number;
	
	public Person(Bitmap img, String name, String number) {
		super();
		this.img = img;
		this.name = name;
		this.number = number;
	}
	public Person(){}
	
	public Bitmap getImg() {
		return img;
	}
	public void setImg(Bitmap img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
