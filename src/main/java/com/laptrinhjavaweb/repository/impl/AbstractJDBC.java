package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultsetMapper;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.repository.GenericJDBC;

public class AbstractJDBC<T> implements GenericJDBC<T> {
	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/estate4month2019";
			String username = "root";
			String password = "123456";
			return DriverManager.getConnection(dbURL, username, password);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<T> query(String sql, Object... parameters) {
		ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
		Connection conn = getConnection();
		// String sql = "SELECT * FROM building";
		try {
			if (conn != null) {
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				return resultsetMapper.mapRow(result, zClass);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return null;
	}

	@Override
	public void Update(String sql, Object... parameters) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn.setAutoCommit(false);
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}
				int rowsInserted = statement.executeUpdate();
				conn.commit();
				if (rowsInserted > 0) {
					System.out.println("A new user was inserted successfully!");
				}

			}
		} catch (SQLException | ClassNotFoundException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				// set parameter to statement
				for (int i = 0; i < parameters.length; i++) {
					int index = i + 1;
					statement.setObject(index, parameters[i]);
				}
				int rowsInserted = statement.executeUpdate();
				result = statement.getGeneratedKeys();
				conn.commit();
				if (rowsInserted > 0) {
					while (result.next()) {
						return result.getLong(1);
					}
				}
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return null;
	}

	@Override
	public Long insert(Object object) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			conn.setAutoCommit(false);
			String sql = createSQLInsert();
			System.out.print(sql);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				// set parameter to statement
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				int index = 0;
				for (int i = 0; i < fields.length; i++) {
					index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}
				Class<?> parentClass = zClass.getSuperclass();
				while (parentClass != null) {
					Field[] fieldsParent = parentClass.getDeclaredFields();
					for (int i = 0; i < fieldsParent.length; i++) {
						index = index + 1;
						Field field = fieldsParent[i];
						field.setAccessible(true);
						statement.setObject(index, field.get(object));
					}
					parentClass = parentClass.getSuperclass();
				}
				int rowsInserted = statement.executeUpdate();
				result = statement.getGeneratedKeys();
				conn.commit();
				if (rowsInserted > 0) {
					while (result.next()) {
						return result.getLong(1);
					}
				}
			}
		} catch (SQLException | IllegalAccessException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return null;
	}

	private String createSQLInsert() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder paramas = new StringBuilder("");
		for (Field field : zClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				paramas.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				paramas.append("?");
			}
		}

		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					paramas.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					paramas.append("?");

				}
			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = "INSERT INTO	" + tableName + "(" + fields.toString() + ") Values(" + paramas.toString() + ")";
		return sql;
	}

	@Override
	public void update(Object object, Long id) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		try {
			conn.setAutoCommit(false);
			String sql = createSQLUpdate();
			System.out.println(sql);
			statement = conn.prepareStatement(sql);
			if (conn != null) {
				// set parameter to statement
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				int index = 0;
				for (int i = 0; i < fields.length; i++) {
					index = i + 1;
					Field field = fields[i];
					field.setAccessible(true);
					statement.setObject(index, field.get(object));
				}
				Class<?> parentClass = zClass.getSuperclass();
				while (parentClass != null) {
					Field[] fieldsParent = parentClass.getDeclaredFields();
					Object idObject = null;
					for (int i = 0; i < fieldsParent.length; i++) {
						index = index + 1;
						Field field = fieldsParent[i];
						field.setAccessible(true);
						String name = field.getName();
						if (name.equals("id")) {
							idObject = field.get(object);
							index--;
						} else
							statement.setObject(index, field.get(object));
					}
					if (idObject != null)
						statement.setObject(index + 1, idObject);
					parentClass = parentClass.getSuperclass();
				}
				statement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException | IllegalAccessException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	private String createSQLUpdate() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		String where = null;
		StringBuilder sets = new StringBuilder("");
		for (Field field : zClass.getDeclaredFields()) {
			if (sets.length() > 1) {
				sets.append(" ,");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				if (!columnName.equals("id")) {
					sets.append(columnName);
					sets.append(" =?");
				}
			}
		}

		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();

				if (!columnName.equals("id")) {
					if (sets.length() > 1) {
						sets.append(" ,");
					}
					sets.append(columnName);
					sets.append(" =?");
				} else {
					where = " Where " + columnName + " = ?";
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = " UPDATE " + tableName + " SET " + sets.toString() + where;
		return sql;
	}

	@Override
	public void delete(long id) {
		Connection conn = getConnection();
		PreparedStatement statement = null;
		try {
			conn.setAutoCommit(false);
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			String sql = "delete from " + tableName + "where id =?";
			statement = conn.prepareStatement(sql);
			if (conn != null) {
				statement.setObject(1, id);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	@Override
	public <T> T findByID(long id) {
		ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
		Connection conn = getConnection();
		// String sql = "SELECT * FROM building";
		PreparedStatement statement = null;
		ResultSet result = null;
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		String sql = "Select *from" + tableName + "where id= ?";
		try {
			if (conn != null) {
				statement = conn.prepareStatement(sql);
				statement.setObject(1, id);
				result = statement.executeQuery(sql);
				return resultsetMapper.mapRow(result, this.zClass).get(0);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return null;
	}

	@Override
	public List<T> findAll(Map<String,Object> properties,Pageble pageble,Object...where ) {
		ResultsetMapper<T> resultsetMapper = new ResultsetMapper<>();
		Connection conn = getConnection();
		// String sql = "SELECT * FROM building";
		Statement statement = null;
		ResultSet result = null;
		StringBuilder sql = createSQLfindAll(properties);
		if (where!=null&&where.length>0) {
			sql.append(where[0]);
		}
		if (pageble!=null) {
			if (pageble.getOffset()!=null&&pageble.getLimit()!=null) {
				sql.append("LIMIT"+pageble+","+pageble.getLimit());
			}
			if (pageble.getSorter()!=null) {
				Sorter sorter= pageble.getSorter();
				sql	.append("ORDER BY"+sorter.getSortName()+" "+sorter.getSortBy()+"");
			}
			
		}
		try {
			if (conn != null) {
				statement = conn.createStatement();
				result = statement.executeQuery(sql.toString());
				return resultsetMapper.mapRow(result, this.zClass);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		return null;
	}

	private StringBuilder createSQLfindAll(Map<String,Object> properties) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder result= new StringBuilder("Select *from "+tableName+"where 1=1");
		if (properties!=null && properties.size()>0) {
			String [] params= new String[properties.size()];
			Object [] value = new Object[properties.size ()];
			int i =0;
			for (Map.Entry<?,?> item:properties.entrySet()) {
				params[i]=item.getKey().toString();
				value[i]=item.getValue();
				i++;
			}
			for (int j=0;j<=i;i++) {
				if (value[j] instanceof String)
				result.append("and LOWER("+params[j]+"LIKE '%"+value[j]+"%'");
				else  if (value[j] instanceof Integer)
					result.append("and"+params[j]+"="+value[j]);	
			}
		}
		return result;
	}
}
