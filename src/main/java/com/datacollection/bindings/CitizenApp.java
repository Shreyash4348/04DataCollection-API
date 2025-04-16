package com.datacollection.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenApp {

	private String fullName;
	
	private String email;
	
	private Long phoneNumber;
	
	private Long ssn;
	
	private LocalDate dob;
	
	private String stateName;
}
