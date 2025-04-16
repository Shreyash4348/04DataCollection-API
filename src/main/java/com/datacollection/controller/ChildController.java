package com.datacollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datacollection.bindings.ChildRequest;
import com.datacollection.bindings.DcSummary;
import com.datacollection.service.IDcService;

@RestController
public class ChildController {

	@Autowired
	private IDcService service;

	@PostMapping("/childrens")
	public ResponseEntity<DcSummary> saveChilds(@RequestBody ChildRequest request) {
		Long caseNum = service.saveChildrens(request);

		DcSummary summary = service.getSummary(caseNum);

		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}
