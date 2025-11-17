/**
 * Copyright © 2025 All rights reserved of preset CODE ... 
 */
package com.ms.bankx.infrastructure.adapter.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ms.bankx.infrastructure.adapter.rest.specification.EmployeeApi;

/**
 * 
 */
@RestController
public class EmployeeController implements EmployeeApi{

	@Override
	public ResponseEntity<String> getTestCall() {
	   HttpHeaders responseHeaders = new HttpHeaders();
	   responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<String>("Test sample :: Hello World this server respose OK!!", responseHeaders, HttpStatus.OK);
	}

}
