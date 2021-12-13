package com.demo.evin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.evin.entity.VaccinesStores;

@Repository
public interface VaccineStoreRepository extends JpaRepository<VaccinesStores, Long>{

}
