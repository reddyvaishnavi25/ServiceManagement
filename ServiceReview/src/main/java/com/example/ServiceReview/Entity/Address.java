package com.example.ServiceReview.Entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	public int addressId;
	public String streetAddress;
	public String city;
	public String state;
	public String country;
	public int zip;

	@ManyToOne(cascade = {CascadeType.ALL})
	@NotFound(action = NotFoundAction.IGNORE)
	public Customer customer;



}
