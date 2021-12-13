package com.demo.evin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.evin.entity.Customer;
import com.demo.evin.model.CustomerPayload;
import com.demo.evin.repository.CustomerRepository;
import com.demo.evin.util.CryptoUtil;
import com.demo.evin.util.ResponseBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServie {

	@Autowired
	public CustomerRepository customerRepository;

	public ResponseEntity<?> saveCustomers(CustomerPayload customerPayload) {
		ResponseBean res = new ResponseBean();
		res.setStatus(HttpStatus.OK);
		Customer customer = new Customer();
		try {
			if(customerPayload.getMobileNo()!=null && !customerPayload.getMobileNo().isEmpty()) {
				customer = customerRepository.findByMobileNoandIsDeletedFalse(customerPayload.getMobileNo());
				if(customer!=null) {
					res.setMessage("User Already Exists with this Mobile Number");
					res.setData(null);
					return new ResponseEntity<>(res, res.getStatus());
				}
			}
			customer = this.setCustomer(customerPayload);
			Customer savedCustomer = customerRepository.save(customer);
			if (customerPayload.getId() != null && customerPayload.getId() > 0) {
				res.setMessage("Record is updated successfully");
				res.setData(savedCustomer);
			} else {
				res.setMessage("Record saved successfully");
				res.setData(savedCustomer);
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			res.setStatus(HttpStatus.EXPECTATION_FAILED);
			res.setData(null);
			res.setMessage("Something went wrong, please try again");
		}
		return new ResponseEntity<>(res, res.getStatus());
	}

	public Customer setCustomer(CustomerPayload payLoad) {
		Customer customer = new Customer();
		String enCrypted;
		if (payLoad.getId() != null && payLoad.getId() > 0) {
			Optional<Customer> oldRecord = customerRepository.findById(payLoad.getId());
			if (oldRecord.isPresent()) {
				customer = oldRecord.get();
				customer.setModifiedOn(new Date());
			}
		}else {
			customer.setCreatedOn(new Date());
		}
		customer.setAge(payLoad.getAge());
		customer.setFirstName(payLoad.getFirstName());
		customer.setLastName(payLoad.getLastName());
		customer.setIsDeleted(payLoad.getIsDelete());
		enCrypted = CryptoUtil.encrypt(payLoad.getPassWord());
		customer.setPassWord(enCrypted);
		customer.setMobileNo(payLoad.getMobileNo());
		
		return customer;
	}

	public ResponseBean loginValidator(String mobileNo, String passWord) {
		ResponseBean res = new ResponseBean();
		res.setStatus(HttpStatus.OK);
		String validPassword;
		String message;
		String deCrypt;
		Customer customer = null;
		try {
			if (mobileNo != null && !mobileNo.isEmpty()) {
				Customer oldRecord = customerRepository.findByMobileNoandIsDeletedFalse(mobileNo);
				if (oldRecord != null) {
					validPassword = oldRecord.getPassWord();
					deCrypt = CryptoUtil.decrypt(validPassword);
					if (passWord.equals(deCrypt)) {
						message = "login Successfull";
						customer = oldRecord;
						res.setData(oldRecord);
					} else {
						message = "Invalid Password";
					}
				} else {
					message = "Invalid User";
				}
				res.setMessage(message);
				res.setData(customer);
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			res.setStatus(HttpStatus.EXPECTATION_FAILED);
			res.setData(null);
			res.setMessage("Something went wrong, please try again");
		}
		return res;
	}

	public ResponseEntity<ResponseBean> getAllCustomers() {
		ResponseBean res = new ResponseBean();
		List<Customer> customerList = new ArrayList<>();
		res.setStatus(HttpStatus.OK);
		try {
			customerList = customerRepository.findAllActiveRecords();
			if (customerList != null && !customerList.isEmpty()) {
				res.setData(customerList);
				res.setMessage("Records Fetched SuccessFully");
			} else {
				res.setData(new ArrayList<>());
				res.setMessage("Records Fetched SuccessFully");
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			res.setStatus(HttpStatus.EXPECTATION_FAILED);
			res.setData(null);
			res.setMessage("Something went wrong, please try again");
		}
		return new ResponseEntity<>(res, res.getStatus());
	}

	public ResponseEntity<ResponseBean> getCustomersById(Long id) {
		ResponseBean res = new ResponseBean();
		res.setStatus(HttpStatus.OK);
		try {
			Optional<Customer> customer = customerRepository.findById(id);
			if (customer.isPresent()) {
				res.setData(customer.get());
				res.setMessage("Records Fetched SuccessFully");
			} else {
				res.setData(null);
				res.setMessage("Records Fetched SuccessFully");
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			res.setStatus(HttpStatus.EXPECTATION_FAILED);
			res.setData(null);
			res.setMessage("Something went wrong, please try again");
		}
		return new ResponseEntity<>(res, res.getStatus());
	}
}
