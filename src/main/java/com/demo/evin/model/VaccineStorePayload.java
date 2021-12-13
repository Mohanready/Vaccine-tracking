package com.demo.evin.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@NotEmpty
public class VaccineStorePayload {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("store_id")
	@NotEmpty(message = "Store Id cant be Empty")
	private Long storeId;
	
	@JsonProperty("vaccine_id")
	@NotEmpty(message = "vaccineId Id cant be Empty")
	private Long vaccineId;
	
	@JsonProperty("available_stock")
	@NotEmpty(message = "Available Stock Id cant be Empty")
	private Long availableStock;
}
