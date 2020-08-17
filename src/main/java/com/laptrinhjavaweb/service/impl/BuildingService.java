package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;

import com.laptrinhjavaweb.convertor.BuildingConvertor;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEnity;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	private IBuildingRepository buildingRepository;
	
	public BuildingService() {
		buildingRepository= new BuildingRepository();
	}
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingConvertor buildingConvertor = new BuildingConvertor();
		BuildingEnity buildingEnity =buildingConvertor.converToEntity(buildingDTO);
		//buildingEnity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		System.out.println(buildingEnity.getCreatedDate());
		buildingRepository.update(buildingEnity,buildingEnity.getId());
		return null;
	}
	
}
