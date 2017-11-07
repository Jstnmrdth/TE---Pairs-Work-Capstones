package com.techelevator.projects.model.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void save(Project newProject) {
		String sqlInsertProject = "INSERT INTO project(project_id, name, from_date, to_date) "+
								  "VALUES (?, ?, ?, ?)";
		
		newProject.setId(getNextProjectId());
		
		jdbcTemplate.update(sqlInsertProject, newProject.getId(),
											 newProject.getName(),
											 newProject.getStartDate(),
											 newProject.getEndDate());
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		List<Project> activeProjects = new ArrayList<Project>();
		String getActiveProjects = "SELECT * " + 
								  "FROM project " +
								  "WHERE to_date IS NULL";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getActiveProjects);
		while(results.next()) {
			activeProjects.add(createProject(results));
		}
		return activeProjects;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String removeEmployee = "DELETE FROM project_employee " +
								"WHERE employee_id = ? " +
								"AND project_id = ?";
		jdbcTemplate.update(removeEmployee, employeeId, projectId);
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
//		Employee searchEmployee = new Employee();
		String addEmployee = "INSERT INTO project_employee (project_id, employee_id) " +
							 "VALUES (?, ?)";
		jdbcTemplate.update(addEmployee, projectId, employeeId);
	}
	
	private Project createProject(SqlRowSet sqlRowSet) {
		Project newProject = new Project();
		newProject.setName(sqlRowSet.getString("name"));
		newProject.setId(sqlRowSet.getLong("project_id"));
		Date date = sqlRowSet.getDate("from_date");
		if(date != null) {
			newProject.setStartDate(date.toLocalDate());
		}
		date = sqlRowSet.getDate("to_date");
		if(date != null) {
			newProject.setEndDate(date.toLocalDate());
		}
		
		return newProject;
	}
	
	private long getNextProjectId() {
		String sqlNextId = "SELECT nextval('seq_project_id')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlNextId);
		result.next();
		return result.getLong(1);
	}
}
