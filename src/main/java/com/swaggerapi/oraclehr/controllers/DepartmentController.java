package com.swaggerapi.oraclehr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swaggerapi.oraclehr.models.Department;
import com.swaggerapi.oraclehr.repository.DepartmentRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/departments")
@Api(value="/departments", tags="Departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository repo;
	
	@GetMapping("/getAllDepartments")
	@ApiResponse(code=200, message="Success")
	public ResponseEntity<List<Department>> GetDepartments(){
		List<Department> reg = null;
		try {
			reg = repo.GetAllDepartments();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Department>>(reg, HttpStatus.OK);
	}
	
	@GetMapping("/getSingleDepartment/{id}")
	@ApiResponse(code=200,message="Success")
	public Department getSingleDepartment(@PathVariable int id){
		Department res = null;
		try {
			res = repo.GetSingleDepartment(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
