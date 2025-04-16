package com.datacollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollection.bindings.Income;
import com.datacollection.service.IDcService;

@RestController
public class IncomeController {

	@Autowired
	private IDcService service;

	@PostMapping("/income")
	public ResponseEntity<Long> saveIncome(@RequestBody Income income) {
		Long caseNum = service.saveIncomeData(income);
		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
	}

}
