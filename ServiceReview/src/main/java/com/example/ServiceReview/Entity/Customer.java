package com.example.ServiceReview.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@ToString
@RequiredArgsConstructor
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "customer_id")
	public Integer customerId;
	@Column(name = "first_name")
	public String firstName;
	@Column(name = "last_name")
	public String lastName;
	@Column(name = "email_id")
	public String emailId;
	@Column(name = "phone_number")
	public Long phoneNumber;
}
