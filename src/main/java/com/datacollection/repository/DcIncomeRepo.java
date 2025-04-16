package com.datacollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollection.entity.DcIncomeEntity;
import java.util.List;


public interface DcIncomeRepo extends JpaRepository<DcIncomeEntity, Integer>{

	public DcIncomeEntity findByCaseNum(Long caseNum);
}
