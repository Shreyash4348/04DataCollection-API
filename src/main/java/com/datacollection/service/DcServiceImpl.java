package com.datacollection.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datacollection.bindings.Child;
import com.datacollection.bindings.ChildRequest;
import com.datacollection.bindings.DcSummary;
import com.datacollection.bindings.Education;
import com.datacollection.bindings.Income;
import com.datacollection.bindings.PlanSelection;
import com.datacollection.entity.CitizenAppEntity;
import com.datacollection.entity.DcCaseEntity;
import com.datacollection.entity.DcChildrenEntity;
import com.datacollection.entity.DcEducation;
import com.datacollection.entity.DcIncomeEntity;
import com.datacollection.entity.PlanEntity;
import com.datacollection.repository.CitizenAppRepo;
import com.datacollection.repository.DcCaseRepo;
import com.datacollection.repository.DcChildrenRepo;
import com.datacollection.repository.DcEducationRepo;
import com.datacollection.repository.DcIncomeRepo;
import com.datacollection.repository.PlanEntityRepo;

@Service
public class DcServiceImpl implements IDcService {

	@Autowired
	private DcCaseRepo caseRepo;

	@Autowired
	private PlanEntityRepo plansRepo;

	@Autowired
	private DcIncomeRepo incomeRepo;

	@Autowired
	private DcEducationRepo eduRepo;

	@Autowired
	private DcChildrenRepo childRepo;

	@Autowired
	private CitizenAppRepo citizenRepo;

	@Override
	public Long loadCaseNumber(Integer appId) {

		Optional<CitizenAppEntity> byId = citizenRepo.findById(appId);
		if (byId.isPresent()) {
			DcCaseEntity entity = new DcCaseEntity();
			entity.setAppId(appId);

			entity = caseRepo.save(entity);
			return entity.getCaseNum();
		}
		return 0l;
	}

	@Override
	public Map<Integer, String> getPlanNames() {
		List<PlanEntity> findPlans = plansRepo.findAll();
		Map<Integer, String> plansMap = new HashMap<>();
		for (PlanEntity entity : findPlans) {
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		}
		return plansMap;
	}

	@Override
	public Long savePlanSelection(PlanSelection selection) {

		Optional<DcCaseEntity> findById = caseRepo.findById(selection.getCaseNum());
		if (findById.isPresent()) {
			DcCaseEntity dcCaseEntity = findById.get();
			dcCaseEntity.setPlanId(selection.getPlanId());
			caseRepo.save(dcCaseEntity);

			return selection.getCaseNum();
		}

		return null;
	}

	@Override
	public Long saveIncomeData(Income income) {
		DcIncomeEntity entity = new DcIncomeEntity();
		BeanUtils.copyProperties(income, entity);
		incomeRepo.save(entity);
		return entity.getCaseNum();
	}

	@Override
	public Long saveEducation(Education education) {
		DcEducation entity = new DcEducation();
		BeanUtils.copyProperties(education, entity);
		eduRepo.save(entity);
		return entity.getCaseNum();
	}

	@Override
	public Long saveChildrens(ChildRequest request) {  // multiple child Data will come from user(front-end)
		
		List<Child> childs = request.getChilds();
		for (Child c : childs) {
			DcChildrenEntity entity = new DcChildrenEntity();
			BeanUtils.copyProperties(c, entity);
			childRepo.save(entity);
		}

		return request.getCaseNum();
	}

	@Override
	public DcSummary getSummary(Long caseNumber) {
		String planName = "";
		DcIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNumber);
		DcEducation educationEntity = eduRepo.findByCaseNum(caseNumber);
		List<DcChildrenEntity> childEntity = childRepo.findByCaseNum(caseNumber);
		Optional<DcCaseEntity> dcCase = caseRepo.findById(caseNumber);

		if (dcCase.isPresent()) {
			Integer planId = dcCase.get().getPlanId();
			Optional<PlanEntity> plan = plansRepo.findById(planId);
			if (plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}

		DcSummary summary = new DcSummary();
		summary.setPlanName(planName);

		Income income = new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(income);

		Education education = new Education();
		BeanUtils.copyProperties(educationEntity, education);
		summary.setEducation(education);

		List<Child> childs = new ArrayList<>();

		for (DcChildrenEntity entity : childEntity) {
			Child childy = new Child();
			BeanUtils.copyProperties(entity, childy);
			childs.add(childy);
		}
		summary.setChilds(childs);

		return summary;
	}

}
