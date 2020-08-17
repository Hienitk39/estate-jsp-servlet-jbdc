package com.laptrinhjavaweb.convertor;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEnity;

public class BuildingConvertor {
	public BuildingDTO converToDTO(BuildingEnity buildingEnity) {
		  ModelMapper modelMapper = new ModelMapper();
		  BuildingDTO result = modelMapper.map(buildingEnity, BuildingDTO.class);
		return result;
	}
	public BuildingEnity converToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEnity result = modelMapper.map(buildingDTO,BuildingEnity.class);
		return result;
	}
}
