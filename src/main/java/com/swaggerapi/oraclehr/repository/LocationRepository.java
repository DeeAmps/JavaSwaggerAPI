package com.swaggerapi.oraclehr.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swaggerapi.oraclehr.configuration.Queries;
import com.swaggerapi.oraclehr.models.Location;

@Repository
public class LocationRepository {

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Location> GetAllLocations() throws Exception{
		List<Location> results = jdbctemplate.query(Queries.GETALLLOCATIONS, (res, rowNum) -> new Location(
				res.getInt("LOCATION_ID"),
				res.getString("STREET_ADDRESS"),
				res.getString("POSTAL_CODE"),
				res.getString("CITY"),
				res.getString("STATE_PROVINCE"),
				res.getString("COUNTRY_ID")
		));
		return results;
	}
	
	public Location GetSingleLocations(int locationId) throws Exception{
		Location results = jdbctemplate.query(String.format(Queries.GETSINGLELOCATION, locationId), (res, rowNum) -> new Location(
				res.getInt("LOCATION_ID"),
				res.getString("STREET_ADDRESS"),
				res.getString("POSTAL_CODE"),
				res.getString("CITY"),
				res.getString("STATE_PROVINCE"),
				res.getString("COUNTRY_ID")
		)).get(0);
		return results;
	}
}
