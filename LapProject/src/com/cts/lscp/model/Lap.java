package com.cts.bscp.model;

import java.io.Serializable;
import java.sql.Connection;
import java.time.LocalDate;


@SuppressWarnings("serial")
public class Lap implements Serializable  {

	private String lapcode;
	private String companytitle;
	private LocalDate releaseDate;
	private double price;

	public Lap() {
		
	}

	public Lap(String lapcode, String companytitle, LocalDate releaseDate, double price) {
		super();
		this.lapcode = lapcode;
		this.companytitle = companytitle;
		this.releaseDate = releaseDate;
		this.price = price;
	}

	public String getlapcode1() {
		return lapcode;
	}

	public void setlapcode1(String lapcode) {
		this.lapcode = lapcode;
	}

	public String getCompanytitle() {
		return companytitle;
	}

	public void setCompanytitle(String companytitle) {
		this.companytitle = companytitle;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Tv [lapcode=" + lapcode + ", companytitle=" + companytitle + ", releaseDate=" + releaseDate + ", price="
				+ price + "]";
	}

	public void setlapcode(String next) {
		// TODO Auto-generated method stub
		
	}

	public String getlapcode() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPublishDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPublishDate(LocalDate localDate) {
		// TODO Auto-generated method stub
		
	}

	public Connection getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

		
	

}
