package com.example.ServiceReview.Controller;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.ServiceReview.repo.AddressRepository;
import com.example.ServiceReview.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ServiceReview.Entity.*;

@RestController
@CrossOrigin(origins="*")
	public class Controller {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;

	@PostMapping("/add/customer")
	public ResponseEntity<Customer> saveCustomerDetails(@RequestBody Customer customerDetails) {
		try{
			Customer customer= new Customer();
			customer.setFirstName(customerDetails.getFirstName());
			customer.setLastName(customerDetails.getLastName());
			customer.setEmailId(customerDetails.getEmailId());
			customer.setPhoneNumber(customerDetails.getPhoneNumber());
			Customer response = customerRepository.save(customer);
			return Objects.nonNull(response) ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

		@PostMapping("/add/address")
		public ResponseEntity<List<Address>> saveCustomerDetails(@RequestBody CustomerAddressModel customerAddressDetails){
		try{
			Optional<Customer> customerDetails = customerRepository.findById(customerAddressDetails.customerId);
			if(Objects.nonNull(customerDetails)) {
				List<Address> addressDetailsList = customerAddressDetails.addressDetails;
				List<Address> addressList = new ArrayList<>();
				if(Objects.nonNull(addressDetailsList) && addressDetailsList.size() > 0) {
					addressDetailsList.stream().forEach((rec) -> {
						Address address = new Address();
						address.setAddressId(null);
						address.setCustomer(customerDetails.isEmpty() ? null : customerDetails.get());
						address.setStreetAddress(rec.getStreetAddress());
						address.setCity(rec.getCity());
						address.setCountry(rec.getCountry());
						address.setState(rec.getState());
						address.setZip(rec.getZip());
						addressList.add(address);
						addressRepository.save(address);
					});
					return addressList.size() > 0 ? ResponseEntity.ok(addressList) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				return addressList.size() > 0 ? ResponseEntity.ok(addressList) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return null;
		}catch (Exception e) {
			return null;
		}
		}

	@GetMapping("/address/details")
	public List<Address> addressDetails(@RequestParam Integer customerId){
		try{
			return addressRepository.findAllByCustomerCustomerId(customerId);
		}catch (Exception e) {
			return null;
		}
	}

	@PutMapping("/update/customer")
	public ResponseEntity<Customer> updateCustomerDetails(@RequestBody Customer customerDetails) {
			try{
				if(Objects.nonNull(customerDetails)) {
					Optional<Customer> customerResponse = customerRepository.findById(customerDetails.customerId);
					if (Objects.nonNull(customerResponse)) {
						customerRepository.save(customerDetails);
					return ResponseEntity.ok(customerDetails);
					}
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		return null;
	}

	@PutMapping("/update/address")
	public ResponseEntity<String> updateAddressDetails(@RequestBody CustomerAddressModel customerAddressDetails) {
		try{
			Optional<Customer> customerDetails = customerRepository.findById(customerAddressDetails.customerId);
			if(Objects.nonNull(customerDetails)) {
				List<Address> addressDetailsList = customerAddressDetails.addressDetails;
				List<Address> addressList = new ArrayList<>();
				if(Objects.nonNull(addressDetailsList) && addressDetailsList.size() > 0) {
					addressDetailsList.forEach((rec) -> {
						Address address = new Address();
						address.setAddressId(rec.getAddressId());
						address.setCustomer(customerDetails.orElse(null));
						address.setStreetAddress(rec.getStreetAddress());
						address.setCity(rec.getCity());
						address.setCountry(rec.getCountry());
						address.setState(rec.getState());
						address.setZip(rec.getZip());
						addressRepository.save(address);
					});
					return ResponseEntity.ok("Address Updated Successfully");
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/all/customer/details")
	public List<Customer> getCustomerDetails(){
		try{
			return customerRepository.findAll();
		}catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/customer/details")
	public Customer getCustomerDetails(@RequestParam Integer customerId){
		try{
			return customerRepository.findById(customerId).get();
		}catch (Exception e) {
			return null;
		}
	}

	@DeleteMapping("/customer/details")
	public ResponseEntity<Boolean> DeleteCustomerDetails(@RequestParam Integer customerId) {
		try{
			List<Integer> idList = addressRepository.findAllByCustomerCustomerId(customerId).stream().map(Address::getAddressId).collect(Collectors.toList());
			addressRepository.deleteAllById(idList);
			customerRepository.deleteById(customerId);
			return ResponseEntity.ok(true);
		}catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/address/details")
	public ResponseEntity<String> DeleteAddressDetails(@RequestParam Integer customerId) {
		try{
			List<Integer> idList = addressRepository.findAllByCustomerCustomerId(customerId).stream().map(Address::getAddressId).collect(Collectors.toList());
			addressRepository.deleteAllById(idList);
			return ResponseEntity.ok("Address Details Deleted Successfully");
		}catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/one/address")
	public ResponseEntity<Boolean> DeleteOneAddressDetails(@RequestParam Integer customerId, @RequestParam Integer addressId) {
		try{
			addressRepository.DeleteByAddressIdAndCustomerCustomerId(addressId, customerId);
			return ResponseEntity.ok(true);
		}catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}


