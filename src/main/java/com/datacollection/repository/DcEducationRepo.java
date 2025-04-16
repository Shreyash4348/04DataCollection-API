package com.datacollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollection.entity.DcEducation;

public interface DcEducationRepo extends JpaRepository<DcEducation, Integer>{

	public DcEducation findByCaseNum(Long caseNum);
}
