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

import com.demo.evin.model.StorePayload;
import com.demo.evin.repository.StoreRepository;
import com.demo.evin.service.StoreService;
import com.demo.evin.util.ResponseBean;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/store")
@Slf4j
public class StoreController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private StoreRepository storeRepository;

	@PostMapping(value = "/saveorupdatestore", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> saveOrUpdateCustomer(@RequestBody StorePayload storePayload, BindingResult result) {
		if (result.hasFieldErrors()) {
			log.error("Error while parsing Store Request Json {}", result.getFieldError());
			return new ResponseEntity<>(PaylaodValidator.setErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		log.debug("save or update store  :  {}", storePayload);
		return storeService.saveStores(storePayload);
	}

	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<ResponseBean> getAllCustomers() {
		log.debug("getting Stores: {}");
		return storeService.getAllStores();
	}

	@GetMapping(value = "getbyid", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> getById(@RequestParam Long id) {
		log.debug("getting Stores By id:", id);
		return storeService.getStoreById(id);
	}

}
