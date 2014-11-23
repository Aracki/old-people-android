package com.example.pomoc_starijima.Sat;

public class _Rodjendan {

	private String ime;
	private String prezime;
	private int id;
	private int day;
	private int month;
	private int year;

	public _Rodjendan(int idR, String i, String p, int d, int m, int year) {
		id = idR;
		ime = i;
		prezime = p;
		day = d;
		month = m;
		this.year = year;
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

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

}
