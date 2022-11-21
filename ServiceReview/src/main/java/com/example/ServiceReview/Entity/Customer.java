package com.example.ServiceReview.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	public int customerId;
	public String first_name;
	public String last_name;
	public String email;
	public int phone;

	

	

}
