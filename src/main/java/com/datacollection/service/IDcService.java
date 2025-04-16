package com.datacollection.service;

import java.util.List;
import java.util.Map;

import com.datacollection.bindings.Child;
import com.datacollection.bindings.ChildRequest;
import com.datacollection.bindings.DcSummary;
import com.datacollection.bindings.Education;
import com.datacollection.bindings.Income;
import com.datacollection.bindings.PlanSelection;

public interface IDcService {

	public Long loadCaseNumber(Integer appId);
	
	public Map<Integer, String> getPlanNames();
	
	public Long savePlanSelection(PlanSelection selection);
	
	public Long saveIncomeData(Income income);
	
	public Long saveEducation(Education education);
	
	public Long saveChildrens(ChildRequest request);
	
	public DcSummary getSummary(Long caseNumber);
	
	
}
