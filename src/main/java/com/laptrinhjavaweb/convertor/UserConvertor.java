package com.laptrinhjavaweb.convertor;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

public class UserConvertor {
	public UserDTO converToDTO(UserEntity userEnity) {
		  ModelMapper modelMapper = new ModelMapper();
		  UserDTO result = modelMapper.map(userEnity, UserDTO.class);
		return result;
	}
	public UserEntity converToEntity(UserDTO userDTO) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity result = modelMapper.map(userDTO,UserEntity.class);
		return result;
	}
}
