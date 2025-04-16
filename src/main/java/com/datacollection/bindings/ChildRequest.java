package com.datacollection.bindings;

import java.util.List;

import lombok.Data;

@Data
public class ChildRequest {

	private Long caseNum;
	
	private List<Child> childs;
}
