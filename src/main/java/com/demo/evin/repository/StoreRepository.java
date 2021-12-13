package com.demo.evin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.evin.entity.Store;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{

	@Query(value = "select * from store where is_deleted = false", nativeQuery = true)
	public List<Store> findAllActiveRecords();

}
