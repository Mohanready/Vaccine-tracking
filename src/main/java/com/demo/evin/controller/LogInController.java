package com.demo.evin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.evin.service.CustomerServie;
import com.demo.evin.util.ResponseBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class LogInController {

	@Autowired
	public CustomerServie customerService;

	@PostMapping(value = "/login")
	public ResponseBean logIn(@RequestBody LoginModel loginModel) {
		log.debug("validating user:{}");
		if (loginModel.getMobileNo() == null) {
			return ResponseBean.builder().message("Please provide password..").data(null).status(HttpStatus.OK).build();
		} else if (loginModel.getMobileNo() == null) {
			return ResponseBean.builder().message("Please provide mobile number..").data(null).status(HttpStatus.OK)
					.build();
		}
		return customerService.loginValidator(loginModel.getMobileNo(), loginModel.getPassword());
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class LoginModel {
		private String mobileNo;
		private String password;

	}
}
