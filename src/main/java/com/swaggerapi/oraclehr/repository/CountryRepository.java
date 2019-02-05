package com.swaggerapi.oraclehr.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swaggerapi.oraclehr.configuration.Queries;
import com.swaggerapi.oraclehr.models.Country;

@Repository
public class CountryRepository {

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Country> GetAllCountries() throws Exception{
		List<Country> results = jdbctemplate.query(Queries.GETALLCOUNTRIES, (res, rowNum) -> new Country(
				res.getString("COUNTRY_ID"),
				res.getString("COUNTRY_NAME"),
				res.getInt("REGION_ID")
		));
		return results;
	}
	
	public Country GetSingleCountry(int countryId) throws Exception{
		Country results = jdbctemplate.query(String.format(Queries.GETSINGLECOUNTRY, countryId), (res, rowNum) -> new Country(
				res.getString("COUNTRY_ID"),
				res.getString("COUNTRY_NAME"),
				res.getInt("REGION_ID")
		)).get(0);
		return results;
	}
}
