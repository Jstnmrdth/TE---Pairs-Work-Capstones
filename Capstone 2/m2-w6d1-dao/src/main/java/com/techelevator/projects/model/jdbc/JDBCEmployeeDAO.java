package com.techelevator.projects.model.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<Employee>();
		String getEmployees = "SELECT * " +
							  "FROM employee ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getEmployees);
		while(results.next()) {
			employeeList.add(createEmployee(results));
		}
		return employeeList;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employeeSearch = new ArrayList<Employee>();
		String searchEmployee = "SELECT * " +
								"FROM employee " +
								"WHERE first_name = ? " +
								"AND last_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(searchEmployee, firstNameSearch, lastNameSearch);
		while(results.next()) {
			employeeSearch.add(createEmployee(results));
		}
		return employeeSearch;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		List<Employee> employeesInDepartment = new ArrayList<Employee>();
		String searchByDepartment = "SELECT * " +
									"FROM employee " +
									"WHERE department_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(searchByDepartment, id);
		while (results.next()) {
			employeesInDepartment.add(createEmployee(results));
		}
		return employeesInDepartment;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> employeesWithoutProject = new ArrayList<Employee>();
		String getNoProjectEmployees = "SELECT * " +
									  "FROM employee " +
									  "WHERE employee.employee_id NOT IN (SELECT employee.employee_ID " +
									  									"FROM employee " +
									  									"JOIN project_employee ON project_employee.employee_id = employee.employee_id)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getNoProjectEmployees);
		while(results.next()) {
			employeesWithoutProject.add(createEmployee(results));
		}
		return employeesWithoutProject;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		List<Employee> getEmployeeProjectId = new ArrayList<Employee>();
		String searchProjectId = "SELECT * " +
								"FROM employee " +
								"JOIN project_employee ON employee.employee_id = project_employee.employee_id " +
								"WHERE project_employee.project_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(searchProjectId, projectId);
		while(results.next()) {
			getEmployeeProjectId.add(createEmployee(results));
		}
		return getEmployeeProjectId;
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		String changeDepartment = "UPDATE employee " +
								  "SET department_id = ? " + 
								  "WHERE employee_id = ?";
		jdbcTemplate.update(changeDepartment, departmentId, employeeId);
	}

	private Employee createEmployee(SqlRowSet results) {
		Employee newEmployee = new Employee();
		newEmployee.setFirstName(results.getString("first_name"));
		newEmployee.setLastName(results.getString("last_name"));
		newEmployee.setHireDate(results.getDate("hire_date").toLocalDate());
		newEmployee.setDepartmentId(results.getLong("department_id"));
		newEmployee.setBirthDay(results.getDate("birth_date").toLocalDate());
		newEmployee.setId(results.getLong("employee_id"));
		newEmployee.setGender(results.getString("gender").charAt(0));
		return newEmployee;
	}
}
