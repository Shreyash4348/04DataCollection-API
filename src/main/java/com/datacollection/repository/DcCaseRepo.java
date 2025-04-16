package com.datacollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollection.entity.DcCaseEntity;
import java.util.List;


public interface DcCaseRepo extends JpaRepository<DcCaseEntity, Long>{

	public DcCaseEntity findByAppId(Integer appId);
}
