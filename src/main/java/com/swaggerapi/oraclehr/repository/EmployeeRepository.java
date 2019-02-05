package com.swaggerapi.oraclehr.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swaggerapi.oraclehr.configuration.Queries;
import com.swaggerapi.oraclehr.models.Employee;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Employee> GetAllEmployees() throws Exception{
		List<Employee> results = jdbctemplate.query(Queries.GETALLEMPLOYEES, (res, rowNum) -> new Employee(
				res.getInt("EMPLOYEE_ID"),
				res.getString("FIRST_NAME"),
				res.getString("LAST_NAME"),
				res.getString("EMAIL"),
				res.getString("PHONE_NUMBER"),
				res.getDate("HIRE_DATE"),
				res.getString("JOB_ID"),
				res.getInt("SALARY"),
				res.getInt("COMMISSION_PCT"),
				res.getInt("MANAGER_ID"),
				res.getInt("DEPARTMENT_ID")
		));
		return results;
	}
	
	public Employee GetSingleEmployee(int employeeId) throws Exception{
		Employee results = jdbctemplate.query(String.format(Queries.GETSINGLEEMPLOYEE, employeeId), (res, rowNum) -> new Employee(
				res.getInt("EMPLOYEE_ID"),
				res.getString("FIRST_NAME"),
				res.getString("LAST_NAME"),
				res.getString("EMAIL"),
				res.getString("PHONENUMBER"),
				res.getDate("HIRE_DATE"),
				res.getString("JOB_ID"),
				res.getInt("SALARY"),
				res.getInt("COMMISSION_PCT"),
				res.getInt("MANAGER_ID"),
				res.getInt("DEPARTMENT_ID")
		)).get(0);
		return results;
	}
}
