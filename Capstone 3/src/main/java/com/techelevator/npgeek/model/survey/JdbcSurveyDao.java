package com.techelevator.npgeek.model.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import com.techelevator.npgeek.model.park.Parks;
import com.techelevator.npgeek.model.park.ParksDao;

@Controller
public class JdbcSurveyDao implements SurveyDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ParksDao parksDao;
	
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void addSurveyResult(Survey survey) { 
		long id = getNextId();
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyId, parkCode, emailAddress, state, activitylevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, id, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
		
	}
	
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyId')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next id from sequence");
		}
		return id;
	}
	
	@Override
	public Map<Parks, Integer> getMostPopular() {
		String sqlReturnHighest = "SELECT COUNT(parkCode), parkCode " +
								  "FROM survey_result " + 
								  "GROUP BY parkCode " +
								  "ORDER BY COUNT DESC " +
								  "LIMIT 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlReturnHighest);
		
		Map<Parks, Integer> parks = new HashMap<Parks, Integer>();
		while (results.next()) {
			Parks park = new Parks();
			park = parksDao.getParkByCode(results.getString("parkCode"));
			parks.put(park, results.getInt("count"));
		}
		
		return parks;
	}
	
}
