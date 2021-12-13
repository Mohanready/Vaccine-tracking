package com.demo.evin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "store")
public class Store extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long id;
	
	@Column(name = "store_name")
	@JsonProperty("store_name")
	private String storeName;
	
	@Column(name = "store_location")
	@JsonProperty("store_location")
	private String storeLocation;
	
	@Column(name = "pin_code")
	@JsonProperty("pin_code")
	private Long pinCode;
	
	@Column(name = "landmark")
	@JsonProperty("land_mark")
	private String landMark;
}
