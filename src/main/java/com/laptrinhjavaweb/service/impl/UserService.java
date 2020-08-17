package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.convertor.UserConvertor;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService {

	@Override
	public UserDTO save(UserDTO newUser) {
		UserConvertor userConvertor = new UserConvertor();
		UserEntity userEntity =  userConvertor.converToEntity(newUser);
		return null;
	}

}
