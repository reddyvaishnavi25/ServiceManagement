package com.example.ServiceReview.Controller;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServiceReview.Entity.*;

@RestController
@CrossOrigin(origins="*")
	public class Controller {

		@GetMapping("Customer")
		public List<Customer> getCustomerDetails(){
			
			return Stream.of(new Customer(1, "vaishnavi", "reddy", "vasihu@gmail.com", 1234),
					new Customer(2, "surya", "gouthu", "surya@gmail.com", 2345),
					new Customer(3, "mrudulaa", "reddy", "mrudula@gmail.com", 3456)).collect(Collectors.toList());
			
	}
}


