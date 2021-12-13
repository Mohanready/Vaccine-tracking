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

@Data
@Entity(name = "vaccines")
@AllArgsConstructor
@NoArgsConstructor
public class Vaccines extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Long id;
	
	@Column(name = "vaccine_name")
	@JsonProperty("vaccine_name")
	private String vaccineName;
	
	@Column(name = "manifacturer")
	@JsonProperty("manifacturer")
	private String manifacturer;
	
}
