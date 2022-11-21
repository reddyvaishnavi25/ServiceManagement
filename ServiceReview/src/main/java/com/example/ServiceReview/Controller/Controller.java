package com.example.ServiceReview.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.ServiceReview.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServiceReview.Entity.*;

@RestController
@CrossOrigin(origins="*")
	public class Controller {

	@Autowired
	public AddressRepository addressRepo;

		@GetMapping("/Address")
		public List<Address> getCustomerDetails(){
			
//			return Stream.of(new Customer(1, "vaishnavi", "reddy", "vasihu@gmail.com", 1234),
//					new Customer(2, "surya", "gouthu", "surya@gmail.com", 2345),
//					new Customer(3, "mrudula", "reddy", "mrudula@gmail.com", 3456)).collect(Collectors.toList());

			Customer customer= new Customer();

			customer.customerId=1;
			customer.first_name="Vaishnavi";
			customer.last_name="Vundyala";
			customer.email="abc@gmail.com";
			customer.phone=1235467887;

			Address address1 = new Address();
			address1.addressId=1;
			address1.customer=customer;
			address1.streetAddress="18418 Dearborn St.";
			address1.city="Northridge";
			address1.country="USA";
			address1.state="CA";
			address1.zip=91325;

			Address address2 = new Address();
			address2.addressId=2;
			address2.customer=customer;
			address2.streetAddress="9151 Darby Ave";
			address2.city="North Carolina";
			address2.state="NC";
			address2.country="USA";
			address2.zip=91326;

			List<Address> l=new ArrayList<>();
			l.add(address1);
			l.add(address2);

			return addressRepo.saveAll(l);

		}

	@GetMapping("addressdetails/{addId}")
	public Address addressDetails(@PathVariable int addId){
			return addressRepo.findById(addId).get();
	}


}


