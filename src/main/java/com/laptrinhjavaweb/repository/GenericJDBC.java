package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.paging.Pageble;

public interface GenericJDBC<T> {
	public  List<T> query(String sql,Object... parameters);
	void Update(String sql, Object... parameters);
	Long insert(String sql, Object... parameters);
	Long insert (Object object);
	void update (Object object, Long id);
	void delete (long id);
	<T> T findByID(long id);
	List<T> findAll(Map<String,Object> properties,Pageble pageble,Object...where );
}
