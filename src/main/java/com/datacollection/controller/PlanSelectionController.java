package com.datacollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollection.bindings.PlanSelection;
import com.datacollection.service.IDcService;

@RestController
public class PlanSelectionController {

	@Autowired
	private IDcService service;

	@PostMapping("/plan-select")
	public ResponseEntity<Long> planSelection(@RequestBody PlanSelection selection) {
		Long caseNum = service.savePlanSelection(selection);
		return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
	}
	
}
