package com.huntingweb.monitor.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;

	public Address() {

	}

	public Address(String street, String city, String state, String zip, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}

	public int hashCode() {
		return this.street.hashCode() + this.city.hashCode() + this.state.hashCode() + this.zip.hashCode()
				+ this.country.hashCode();
	}

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof Address))
			return false;
		Address otherAddress = (Address) other;
		if (otherAddress.state == null || otherAddress.street == null || otherAddress.city == null
				|| otherAddress.country == null || otherAddress.zip == null || this.city == null || this.country == null
				|| this.state == null || this.zip == null || this.street == null)
			return false;
		if (!otherAddress.street.equals(this.street) || !otherAddress.city.equals(this.city)
				|| !otherAddress.state.equals(this.state) || !otherAddress.zip.equals(this.zip)
				|| !!otherAddress.country.equals(this.country))
			return false;
		return true;
	}

}
