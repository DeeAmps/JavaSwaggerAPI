package com.swaggerapi.oraclehr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swaggerapi.oraclehr.models.Country;
import com.swaggerapi.oraclehr.repository.CountryRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/countries")
@Api(value="/countries", tags="Countries")
public class CountryController {
	
	@Autowired
	private CountryRepository repo;
	
	@GetMapping("/getAllCountries")
	@ApiResponse(code=200,message="Success")
	public ResponseEntity<List<Country>> GetAllCountries(){
		List<Country> res = null;
		try {
			res = repo.GetAllCountries();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Country>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getSingleCountry/{id}")
	@ApiResponse(code=200,message="Success")
	public Country getSingleCountry(@PathVariable int id){
		Country res = null;
		try {
			res = repo.GetSingleCountry(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
