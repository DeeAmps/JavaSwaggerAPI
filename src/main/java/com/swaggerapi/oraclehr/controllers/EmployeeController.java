package com.swaggerapi.oraclehr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swaggerapi.oraclehr.models.Employee;
import com.swaggerapi.oraclehr.repository.EmployeeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/employees")
@Api(value="/employees", tags="Employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping("/getAllEmployees")
	@ApiResponse(code=200, message="Success")
	public ResponseEntity<List<Employee>> GetEmployees(){
		List<Employee> reg = null;
		try {
			reg = repo.GetAllEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Employee>>(reg, HttpStatus.OK);
	}
	
	@GetMapping("/getSingleEmployee/{id}")
	@ApiResponses(value = { 
			@ApiResponse(code=200,message="Success"), 
			@ApiResponse(code=404,message="Employee Not Found"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public ResponseEntity<Employee> getSingleEmployee(@PathVariable int id){
		Employee res = null;
		HttpStatus status = null;
		try {
			res = repo.GetSingleEmployee(id);
			if(res == null) {
				status = HttpStatus.NOT_FOUND;
			}
			else {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Employee>(res, status);
	}

}
