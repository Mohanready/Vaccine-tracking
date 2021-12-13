package com.demo.evin.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBean {

	private HttpStatus status;
	
	private String message;
	
	private Object data;
}
