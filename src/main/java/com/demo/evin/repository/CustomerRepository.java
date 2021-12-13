package com.demo.evin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.demo.evin.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


	@Query(value= "select * from customer where is_deleted = false",nativeQuery = true)
	public List<Customer> findAllActiveRecords();
	
	@Query(value = "select * from customer where mobile_no = ?1 and is_deleted = false",nativeQuery = true)
	public Customer findByMobileNoandIsDeletedFalse(String mobileNo);

}
