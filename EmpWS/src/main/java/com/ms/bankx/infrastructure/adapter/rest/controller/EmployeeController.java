/**
 * Copyright © 2025 BANKX. All rights reserved. The software and hardware products described in this
 * documentation/material are the intellectual property of BANKX. Any reproduction or unauthorized
 * use, without the prior express written consent of BANKX, is strictly prohibited.
 */
package com.ms.bankx.infrastructure.adapter.rest.controller;

import com.ms.bankx.infrastructure.adapter.rest.specification.EmployeeApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 */
@RestController
public class EmployeeController implements EmployeeApi {

  @Override
  public ResponseEntity<String> getTestCall() {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("MyResponseHeader", "MyValue");
    return new ResponseEntity<String>("Test sample :: Hello World this server respose OK!!",
        responseHeaders, HttpStatus.OK);
  }

}
