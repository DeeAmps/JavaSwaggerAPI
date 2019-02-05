package com.swaggerapi.oraclehr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swaggerapi.oraclehr.models.Location;
import com.swaggerapi.oraclehr.repository.LocationRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/locations")
@Api(value="/locations", tags="Locations")
public class LocationController {
	
	@Autowired
	private LocationRepository repo;
	
	@GetMapping("/getAllLocation")
	@ApiResponse(code=200, message="Success")
	public ResponseEntity<List<Location>> GetLocations(){
		List<Location> reg = null;
		try {
			reg = repo.GetAllLocations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Location>>(reg, HttpStatus.OK);
	}
	
	@GetMapping("/getSingleLocation/{id}")
	@ApiResponse(code=200,message="Success")
	public Location getSingleLocation(@PathVariable int id){
		Location res = null;
		try {
			res = repo.GetSingleLocations(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


}
