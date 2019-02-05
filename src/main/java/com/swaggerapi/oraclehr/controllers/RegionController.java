package com.swaggerapi.oraclehr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.swaggerapi.oraclehr.models.Region;
import com.swaggerapi.oraclehr.repository.RegionRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/regions")
@Api(value="/regions", produces ="application/json", tags="Regions")
public class RegionController {
	
	@Autowired
	private RegionRepository repo;
	
	@GetMapping("/getAllRegions")
	@ApiResponse(code=200, message="Success")
	public ResponseEntity<List<Region>> GetRegions(){
		List<Region> reg = null;
		try {
			reg = repo.GetAllRegions();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Region>>(reg, HttpStatus.OK);
	}
	
	@GetMapping("/getSingleRegion/{id}")
	@ApiResponse(code=200,message="Success")
	public Region getSingleRegion(@PathVariable int id){
		Region res = null;
		try {
			res = repo.GetSingleRegion(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@PostMapping("/addNewRegion")
	@ApiResponses(value = { 
			@ApiResponse(code=201,message="Created"), 
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public @ResponseStatus HttpStatus addNewRegion(@RequestBody Region regionModel){
		HttpStatus status;
		try {
			repo.AddNewRegion(regionModel);
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return status;
	}
	
	@DeleteMapping("/removeRegion")
	@ApiResponses(value = { 
			@ApiResponse(code=404,message="Deleted"), 
			@ApiResponse(code=500,message="Internal Server Error")
	})
	public @ResponseStatus HttpStatus removeRegion(@PathVariable int id){
		HttpStatus status;
		try {
			repo.DeleteRegion(id);
			status = HttpStatus.NOT_FOUND;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return status;
	}
	
	@PutMapping("/UpdateRegion/{id}")
	@ApiResponse(code=200,message="Success")
	public ResponseEntity<Region> updateRegion(@PathVariable int id, @RequestBody Region regionModel){
		HttpStatus status;
		Region res = null;
		try {
			res = repo.UpdateRegion(id, regionModel);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
			
		}
		return new ResponseEntity<Region>(res, status);
	}
}
