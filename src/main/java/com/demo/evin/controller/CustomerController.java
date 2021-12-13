package com.demo.evin.controller;

import javax.validation.constraints.PaylaodValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.evin.model.CustomerPayload;
import com.demo.evin.service.CustomerServie;
import com.demo.evin.util.ResponseBean;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServie customerService;

	@PostMapping(value = "/saveorupdate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> saveOrUpdateCustomer(@RequestBody CustomerPayload customerPayload, BindingResult result) {
		if (result.hasFieldErrors()) {
			log.error("Error while parsing Customer Request Json {}", result.getFieldError());
			return new ResponseEntity<>(PaylaodValidator.setErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
	log.debug("save or update customer : {}", customerPayload);
	return customerService.saveCustomers(customerPayload);
	}

	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<ResponseBean> getAllCustomers() {
		log.debug("get all customers : {}");
		return customerService.getAllCustomers();
	}

	@GetMapping(value = "/getbyid", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> getById(@RequestParam Long id) {
		log.debug("get customers by id:{}", id);
		return customerService.getCustomersById(id);
	}
}
