package com.example.pomoc_starijima.Sat;


public class _Pomen {

	private String ime;
	private int id;
	private int day;
	private int month;
	private int year;
	private String mesto;

	public _Pomen(int idR, String i,String mesto, int d, int m, int year) {
		this.id = idR;
		this.ime = i;
		this.day = d;
		this.month = m;
		this.year = year;
		this.mesto = mesto;
		// TODO Auto-generated constructor stub
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	
	public String getMesto() {
		return mesto;
	}

	public void setSat(String mesto) {
		this.mesto = mesto;
	}

}


