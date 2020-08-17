package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.dto.BuildingDTO;

public class BuildingMapper implements RowMapper<BuildingDTO> {

	@Override
	public BuildingDTO mapRow(ResultSet rs) {
		try {
			BuildingDTO buildingModel = new BuildingDTO();
			buildingModel.setName(rs.getString("name"));
			buildingModel.setStreet(rs.getString("street"));
			buildingModel.setStrucutre(rs.getString("structure"));
			buildingModel.setWard(rs.getString("ward"));
			buildingModel.setNumberOfBasement(rs.getInt("numberofbasement"));
			buildingModel.setBuildingArea(rs.getInt("buildingarea"));
			return buildingModel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
