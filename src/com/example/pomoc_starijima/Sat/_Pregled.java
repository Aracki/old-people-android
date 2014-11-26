package com.example.pomoc_starijima.Sat;


public class _Pregled {

	private String imeBolnice;
	private int id;
	private int day;
	private int month;
	private int year;
	private int sat;
	private int minut;

	public _Pregled(int idR, String i, int d, int m, int year, int sat, int minut) {
		id = idR;
		imeBolnice = i;
		day = d;
		month = m;
		this.year = year;
		this.sat = sat;
		this.minut = minut;
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

	public String getImeBolnice() {
		return imeBolnice;
	}

	public void setImeBolnice(String ime) {
		this.imeBolnice = ime;
	}

	public int getMinut() {
		return minut;
	}

	public void setMinut(int minut) {
		this.minut = minut;
	}
	
	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

}
