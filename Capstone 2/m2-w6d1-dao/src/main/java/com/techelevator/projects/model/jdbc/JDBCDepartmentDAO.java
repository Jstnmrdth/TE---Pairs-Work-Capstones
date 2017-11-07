package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> departments = new ArrayList<>();
		
		String sqlAllDepartments = "SELECT department_id, name " + 
								  "FROM department ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlAllDepartments);
		while(results.next()) {
			Department theDepartment = new Department();
			theDepartment.setId(results.getLong("department_id"));
			theDepartment.setName(results.getString("name"));
			
			departments.add(theDepartment);
		}
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		List<Department> searchDepartments = new ArrayList<Department>();
		String searchForDepartment = "SELECT department_id, name " +
							      "FROM department " +
							      "WHERE name LIKE ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(searchForDepartment, "%"+nameSearch+"%");
		while(results.next()) {
			Department department = new Department();
			department.setId(results.getLong("department_id"));
			department.setName(results.getString("name"));
			searchDepartments.add(department);
		}
		return searchDepartments;
	}

	@Override
	public void updateDepartmentName(Long departmentId, String departmentName) {
		String updateDepartment = "UPDATE department " +
								 "SET name = ? " +
								 "WHERE department_id = ?";
		jdbcTemplate.update(updateDepartment, departmentName, departmentId);
	}

	@Override
	public Department createDepartment(String departmentName) {
		String sqlNextId = "SELECT nextval('seq_department_id')"; 
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlNextId);
		if(result.next()) {
			long id = result.getLong(1);

			Department newDepartment = new Department();
			String createNewDepartment = "INSERT INTO department (department_id, name) " +
										"VALUES (?, ?)";
			jdbcTemplate.update(createNewDepartment, id, departmentName);
			newDepartment.setName(departmentName);
			newDepartment.setId(id);
			return newDepartment;
		} else {
			return null;
		}
	}

	@Override
	public Department getDepartmentById(Long id) {
		Department getDepartment = new Department();
		String findDepartmentById = "SELECT name " +
									"FROM department " +
									"WHERE department_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(findDepartmentById, id);
		while(results.next()) {
			getDepartment.setName(results.getString("name"));
		}
		return getDepartment;
	}

}
