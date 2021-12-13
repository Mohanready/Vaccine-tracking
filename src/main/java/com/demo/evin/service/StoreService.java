package com.demo.evin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.evin.entity.Store;
import com.demo.evin.model.StorePayload;
import com.demo.evin.repository.StoreRepository;
import com.demo.evin.util.ResponseBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;

	public ResponseEntity<?> saveStores(StorePayload storePayload) {
		ResponseBean res = new ResponseBean();
		res.setStatus(HttpStatus.OK);
		try {
			Store store = this.setStore(storePayload);
			Store savedStore = storeRepository.save(store);
			if (storePayload.getId() != null && storePayload.getId() > 0) {
				res.setMessage("Record is updated successfully");
				res.setData(savedStore);
			} else {
				res.setMessage("Record saved successfully");
				res.setData(savedStore);
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			res.setStatus(HttpStatus.EXPECTATION_FAILED);
			res.setData(null);
			res.setMessage("Something went wrong, please try again");
		}
		return new ResponseEntity<>(res, res.getStatus());
	}

	public Store setStore(StorePayload storePayload) {
		Store store = new Store();
		if (storePayload.getId() != null && storePayload.getId() > 0) {
			Optional<Store> oldStore = storeRepository.findById(storePayload.getId());
			if (oldStore.isPresent()) {
				store = oldStore.get();
				store.setModifiedOn(new Date());
			}
		} else {
			store.setCreatedOn(new Date());
		}
		store.setIsDeleted(storePayload.getIsDeleted());
		store.setLandMark(storePayload.getLandMark());
		store.setPinCode(storePayload.getPinCode());
		store.setStoreLocation(storePayload.getStoreLocation());
		store.setStoreName(storePayload.getStoreName());
		return store;
	}

	public ResponseEntity<ResponseBean> getAllStores() {
		ResponseBean res = new ResponseBean();
		List<Store> storeList = new ArrayList<>();
		res.setStatus(HttpStatus.OK);
		try {
			storeList = storeRepository.findAllActiveRecords();
			if (storeList != null && !storeList.isEmpty()) {
				res.setData(storeList);
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

	public ResponseEntity<ResponseBean> getStoreById(Long id) {
		ResponseBean res = new ResponseBean();
		res.setStatus(HttpStatus.OK);
		try {
			Optional<Store> customer = storeRepository.findById(id);
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
