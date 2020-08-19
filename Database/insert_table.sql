use estate4month2019;
create table rentarea{
	id bigint not null primary key auto_increment,
	value varchar(255) not null,
	buildingid bigint not null,
	createddate timestamp null,
	modifieddate timestamp null,
	createdby varchar(255) null,
	modifiedby varchar(255) null
}
alter table rentarea add constraint fk_rentarea_building foreign key (buildingid) references building(id);
