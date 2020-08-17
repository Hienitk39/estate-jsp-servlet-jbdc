package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.BuildingEnity;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEnity> implements IBuildingRepository  {

	//@Override
/*	public Long insert(BuildingEnity entity) {
		StringBuilder sql = new StringBuilder("INSERT into building (name,numberofbasement,buildingarea,district,ward,structure,costrent,costdescription,servicecost,");
		sql.append("carcost,motorbikecost,overtimecost,electricitycost,deposit,payment,timecontract,timedeocrator,managername");
		sql.append(",managerphone,type,createddate,modifiedate,createdby,modifiedby)");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return this.insert(sql.toString(),entity.getName(),entity.getNumberOfBasement());
	}*/

}
