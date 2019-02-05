package com.swaggerapi.oraclehr.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swaggerapi.oraclehr.models.*;
import com.swaggerapi.oraclehr.configuration.*;

@Repository
public class RegionRepository {
	
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Region> GetAllRegions() throws Exception{
		List<Region> results = jdbctemplate.query(Queries.GETALLREGIONS, (res, rowNum) -> new Region(res.getInt("REGION_ID"), res.getString("REGION_NAME")));
		return results;
	}
	
	
	public Region GetSingleRegion(int regionId) throws Exception{
		Region results = jdbctemplate.query(String.format(Queries.GETSINGLEREGIONS, regionId), (res, rowNum) ->
		new Region(res.getInt("REGION_ID"), res.getString("REGION_NAME"))).get(0);
		return results;
	}
	
	public void AddNewRegion(Region regionModel) throws Exception {
		int reslts = jdbctemplate.queryForObject(Queries.GETLASTREGIONROW, Integer.class);
		String query = String.format(Queries.INSERTREGION, reslts + 1 , regionModel.getRegionName());
		jdbctemplate.execute(query);
		return;
	}
	
	public void DeleteRegion(int id) throws Exception {
		String query = String.format(Queries.DELETEREGION, id);
		jdbctemplate.execute(query);
		return;
	}
	
	public Region UpdateRegion(int id, Region regionModel) throws Exception {
		String query = String.format(Queries.UPDATEREGION, regionModel.getRegionName(), id);
		jdbctemplate.execute(query);
		Region results = jdbctemplate.query(String.format(Queries.GETSINGLEREGIONS, id), (res, rowNum) ->
		new Region(res.getInt("REGION_ID"), res.getString("REGION_NAME"))).get(0);
		return results;
	}
}
