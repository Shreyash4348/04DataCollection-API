package com.datacollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollection.bindings.Education;
import com.datacollection.service.IDcService;

@RestController
public class EducationController {

	@Autowired
	private IDcService service;

	@PostMapping("/education")
	public ResponseEntity<Long> saveEducation(@RequestBody Education education) {
		Long caseNum = service.saveEducation(education);
		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
	}

}
