package com.demo.evin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

	@Column(name = "created_on", insertable = true, updatable = false)
	@JsonProperty("created_on")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	private Date createdOn;
	
	@Column(name = "modified_on", insertable = false, updatable = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	@JsonProperty("modified_on")
	private Date modifiedOn;
	
	@Column(name = "is_deleted")
	@JsonProperty("is_deleted")
	private Boolean isDeleted;
}
