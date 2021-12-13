package com.demo.evin.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorePayload {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("store_name")
	private String storeName;
	
	@JsonProperty("store_location")
	private String storeLocation;
		
	@JsonProperty("pin_code")
	private Long pinCode;
	
	@JsonProperty("landmark")
	private String landMark;
	
	@JsonProperty("is_delete")
	private Boolean isDeleted;
	
	@JsonProperty("created_on")
	private Date createdOn;
	
	@JsonProperty
	private Date modifiedOn;
}
