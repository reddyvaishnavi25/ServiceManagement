package com.example.ServiceReview.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@ToString
@RequiredArgsConstructor
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "address_id")
	public Integer addressId;
	@Column(name = "street_address")
	public String streetAddress;
	@Column(name = "city")
	public String city;
	@Column(name = "state")
	public String state;
	@Column(name = "country")
	public String country;
	@Column(name = "zip")
	public Long zip;
	@ManyToOne()
	@NotFound(action = NotFoundAction.IGNORE)
	public Customer customer;

}
