package com.demo.evin.service;

import java.util.Date;
import java.util.Optional;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.evin.entity.Store;
import com.demo.evin.entity.Vaccines;
import com.demo.evin.entity.VaccinesStores;
import com.demo.evin.model.VaccineStorePayload;
import com.demo.evin.repository.StoreRepository;
import com.demo.evin.repository.VaccineRepository;
import com.demo.evin.repository.VaccineStoreRepository;
import com.demo.evin.util.ResponseBean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VaccineStoreService {

	@Autowired
	private VaccineStoreRepository vaccineStoreRepository;

	@Autowired
	private StoreRepository soreRepository;

	@Autowired
	private VaccineRepository vaccineRepository;

	public ResponseEntity<?> saveVaccineStore(VaccineStorePayload vaccineStorePayload) {
		ResponseBean res = new ResponseBean();
		res.setStatus(HttpStatus.OK);
		String message = "";
		VaccinesStores vaccineStore = new VaccinesStores();
		try {
			if (vaccineStorePayload.getStoreId() != null && vaccineStorePayload.getStoreId() > 0) {
				Optional<Store> store = soreRepository.findById(vaccineStorePayload.getStoreId());
				if (!store.isPresent()) {
					message = "Please Valid  Provide a Valid StoreId";
					res.setMessage(message);
					res.setData(null);
					return new ResponseEntity<>(res, res.getStatus());
				}
			}

			if (vaccineStorePayload.getVaccineId() != null && vaccineStorePayload.getVaccineId() > 0) {
				Optional<Vaccines> vaccine = vaccineRepository.findById(vaccineStorePayload.getStoreId());
				if (!vaccine.isPresent()) {
					message = "Please Valid Provide a Valid Vaccine Id";
					res.setMessage(message);
					res.setData(null);
					return new ResponseEntity<>(res, res.getStatus());
				}
			}
			
			vaccineStore = this.setVaccineStore(vaccineStorePayload);
			VaccinesStores savedVaccineStore = vaccineStoreRepository.save(vaccineStore);
			if (vaccineStorePayload.getId() != null && vaccineStorePayload.getId() > 0) {
				res.setData(savedVaccineStore);
				res.setMessage("updated Successfully");
			} else {
				res.setData(savedVaccineStore);
				res.setMessage("saved Successfully");
			}
		} catch (Exception e) {
			log.error("Exception occured : {}", e);
			res.setStatus(HttpStatus.EXPECTATION_FAILED);
			res.setData(null);
			res.setMessage("Something went wrong, please try again");
		}
		return new ResponseEntity<>(res, res.getStatus());
	}

	private VaccinesStores setVaccineStore(VaccineStorePayload vaccineStorePayload) {
		VaccinesStores vaccineStore = null;
		if (vaccineStorePayload.getId() != null && vaccineStorePayload.getId() > 0) {
			Optional<VaccinesStores> oldRecord = vaccineStoreRepository.findById(vaccineStorePayload.getId());
			if (oldRecord.isPresent()) {
				vaccineStore = oldRecord.get();
				vaccineStore.setModifiedOn(new Date());
			}
		} else {
			vaccineStore.setCreatedOn(new Date());
		}

//		if (vaccineStorePayload.getStoreId() != null && vaccineStorePayload.getStoreId() > 0) {
//			Optional<Store> store = soreRepository.findById(vaccineStorePayload.getStoreId());
//			if (store.isPresent()) {
				vaccineStore.setStoreId(vaccineStorePayload.getStoreId());
//			}
//		}
//
//		if (vaccineStorePayload.getVaccineId() != null && vaccineStorePayload.getVaccineId() > 0) {
//			Optional<Vaccines> vaccine = vaccineRepository.findById(vaccineStorePayload.getStoreId());
//			if (vaccine.isPresent()) {
				vaccineStore.setVaccineId(vaccineStorePayload.getVaccineId());
//			}
//		}
		vaccineStore.setAvailableStock(vaccineStorePayload.getAvailableStock());
		return vaccineStore;
	}

}
