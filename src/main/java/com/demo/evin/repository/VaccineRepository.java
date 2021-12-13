package com.demo.evin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.evin.entity.Vaccines;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccines, Long>{

}
