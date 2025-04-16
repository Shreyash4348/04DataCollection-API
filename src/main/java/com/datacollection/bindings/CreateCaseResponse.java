package com.datacollection.bindings;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class CreateCaseResponse {

	Long caseNumber;
	
	private Map<Integer, String> planNames;
}
