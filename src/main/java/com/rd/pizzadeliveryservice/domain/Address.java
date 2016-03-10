package com.rd.pizzadeliveryservice.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private String country;
	private String town;
	private String street;
	private String building;
	private String flat;
	
	public Address() {
	}

	public Address(Long id, String country, String town, String street, String building, String flat) {
		this.id = id;
		this.country = country;
		this.town = town;
		this.street = street;
		this.building = building;
		this.flat = flat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}
}
