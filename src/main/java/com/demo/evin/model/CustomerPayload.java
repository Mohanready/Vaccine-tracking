package com.demo.evin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPayload {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("first_name")
	@NotEmpty(message = "First Name Can not be Empty")
	private String firstName;
	
	@JsonProperty("last_name")
	@NotEmpty(message = "Last Name Can not be Empty")
	private String lastName;
	
	@JsonProperty("age")
	@NotEmpty(message = "Age Name Can not be Empty")
	private Integer age;
	
	@JsonProperty("password")
	@NotEmpty(message = "Password Can not be Empty")
	private String passWord;
	
	@JsonProperty("mobile_no")
	@NotEmpty(message = "Mobile Number Name Can not be Empty")
	private String mobileNo;
	
	@JsonProperty("is_deleted")
	private Boolean isDelete;

}
