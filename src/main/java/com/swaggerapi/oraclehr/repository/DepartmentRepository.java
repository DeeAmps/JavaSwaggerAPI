package com.swaggerapi.oraclehr.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swaggerapi.oraclehr.configuration.Queries;
import com.swaggerapi.oraclehr.models.Department;

@Repository
public class DepartmentRepository {

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Department> GetAllDepartments() throws Exception{
		List<Department> results = jdbctemplate.query(Queries.GETALLDEPARTMENTS, (res, rowNum) -> new Department(
				res.getInt("DEPARTMENT_ID"),
				res.getString("DEPARTMENT_NAME"),
				res.getInt("MANAGER_ID"),
				res.getInt("LOCATION_ID")
		));
		return results;
	}
	
	public Department GetSingleDepartment(int departmentId) throws Exception{
		Department results = jdbctemplate.query(String.format(Queries.GETSINGLEDEPARTMENT, departmentId), (res, rowNum) -> new Department(
				res.getInt("DEPARTMENT_ID"),
				res.getString("DEPARTMENT_NAME"),
				res.getInt("MANAGER_ID"),
				res.getInt("LOCATION_ID")
		)).get(0);
		return results;
	}
}
