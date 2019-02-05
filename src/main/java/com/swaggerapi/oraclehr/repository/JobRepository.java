package com.swaggerapi.oraclehr.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.swaggerapi.oraclehr.configuration.Queries;
import com.swaggerapi.oraclehr.models.Job;

@Repository
public class JobRepository {

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public List<Job> GetAllJobs() throws Exception{
		List<Job> results = jdbctemplate.query(Queries.GETALLJOBS, (res, rowNum) -> new Job(
				res.getString("JOB_ID"),
				res.getString("JOB_TITLE"),
				res.getInt("MIN_SALARY"),
				res.getInt("MAX_SALARY")
		));
		return results;
	}
	
	public Job GetSingleJob(int jobId) throws Exception{
		Job results = jdbctemplate.query(String.format(Queries.GETSINGLEEMPLOYEE, jobId), (res, rowNum) -> new Job(
				res.getString("JOB_ID"),
				res.getString("JOB_TITLE"),
				res.getInt("MIN_SALARY"),
				res.getInt("MAX_SALARY")
		)).get(0);
		return results;
	}
}
