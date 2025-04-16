package com.datacollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datacollection.entity.PlanEntity;

public interface PlanEntityRepo extends JpaRepository<PlanEntity, Integer>{

}
