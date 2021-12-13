package com.demo.evin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vaccine_stores")
public class VaccinesStores extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long id;
	
	@Column(name = "store_id")
	@JsonProperty("store_id")
	private Long storeId;

	@Column(name = "vaccine_id")
	@JsonProperty("vaccine_id")
	private Long vaccineId;
	
	@Column(name = "available_stock")
	@JsonProperty("available_stock")
	private Long availableStock;
}
