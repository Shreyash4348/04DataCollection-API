package com.datacollection.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.datacollection.bindings.CreateCaseResponse;
import com.datacollection.service.IDcService;

@RestController
public class CreateCaseController {

	@Autowired
	private IDcService service;

	@GetMapping("/create-case/{appId}")  //this method is responsible for getting application id and provide case number with different plan names
	public ResponseEntity<CreateCaseResponse> loadCaseNumber(@PathVariable Integer appId) {

		Long caseNumber = service.loadCaseNumber(appId);
		Map<Integer, String> planNames = service.getPlanNames();

		CreateCaseResponse response = new CreateCaseResponse();
		response.setCaseNumber(caseNumber);
		response.setPlanNames(planNames);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
