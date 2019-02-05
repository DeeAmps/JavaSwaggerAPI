package com.swaggerapi.oraclehr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swaggerapi.oraclehr.models.Job;
import com.swaggerapi.oraclehr.repository.JobRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/jobs")
@Api(value="/jobs", tags="Jobs")
public class JobController {
	
	@Autowired
	private JobRepository repo;
	
	@GetMapping("/getAllJobs")
	@ApiResponse(code=200, message="Success")
	public ResponseEntity<List<Job>> GetJobs(){
		List<Job> reg = null;
		try {
			reg = repo.GetAllJobs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Job>>(reg, HttpStatus.OK);
	}
	
	@GetMapping("/getSingleJob/{id}")
	@ApiResponse(code=200,message="Success")
	public Job getSingleJob(@PathVariable int id){
		Job res = null;
		try {
			res = repo.GetSingleJob(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
