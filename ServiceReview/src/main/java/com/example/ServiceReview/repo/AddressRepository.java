package com.example.ServiceReview.repo;

import com.example.ServiceReview.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByCustomerCustomerId(Integer customerId);

    @Modifying
    @Transactional
    @Query(value = "delete from address where address_id = ?1 and customer_customer_id = ?2", nativeQuery = true)
    void DeleteByAddressIdAndCustomerCustomerId(Integer addressId, Integer customerId);
}
