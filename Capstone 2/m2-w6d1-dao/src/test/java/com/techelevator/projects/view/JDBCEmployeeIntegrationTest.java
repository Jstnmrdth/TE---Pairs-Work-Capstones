package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;

public class JDBCEmployeeIntegrationTest {
	private static SingleConnectionDataSource dataSource;
	private JDBCEmployeeDAO dao;
	DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private JdbcTemplate jdbcTemplate;
	private Employee testEmployee;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException{
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		dao = new JDBCEmployeeDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sqlGetEmployeeId = "SELECT nextval('seq_employee_id')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetEmployeeId);
		result.next();
		long id = result.getLong(1);
		
		testEmployee = getEmployee(id, 1, "John", "Smith", "1980-10-01", "M", "2017-10-03");
		String addEmployeeSQL = "INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, gender, hire_date) " +
							    "VALUES (?, 1, 'John', 'Smith', '1980-10-01', 'M', '2017-10-03')";
		jdbcTemplate.update(addEmployeeSQL, id);
	}

	@After
	public void rollback () throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void get_all_employees() {
		List<Employee> results = dao.getAllEmployees();
		
		Assert.assertNotNull("The results should NOT be null", results);
		Assert.assertTrue("Search should return at least one employeee", results.size()>0);
	}
	
	@Test
	public void search_employee_by_name() {
		List<Employee> results = dao.searchEmployeesByName(testEmployee.getFirstName(), testEmployee.getLastName());
		
		Assert.assertNotNull("Should find an employee", results);
		Assert.assertTrue("Search should return at least one employeee", results.size()>0);
	}
	
	@Test
	public void search_employee_by_department() {
		List<Employee> results = dao.getEmployeesByDepartmentId(1);
		
		Assert.assertNotNull("Search should return an employee", results);
		Assert.assertTrue("Search should return at least one employeee", results.size()>0);
	}
	
	@Test
	public void get_employees_without_projects() {
		List<Employee> results = dao.getEmployeesWithoutProjects();
		
		Assert.assertNotNull("Search should bring back an employee without a project", results);
		Assert.assertTrue("Search should return at least one employeee", results.size() > 0);
	}
	
	@Test
	public void get_employee_by_project_id() {		
		List<Employee> results = dao.getEmployeesByProjectId(1L);
		
		Assert.assertNotNull("Search should return at least one employeee", results);
		Assert.assertTrue("Search should return at least one employeee", results.size()>0);
	}
	
	@Test
	public void change_employee_department() {
		dao.changeEmployeeDepartment(testEmployee.getId(), 3L);
		List<Employee> results = dao.getEmployeesByDepartmentId(3L);
		
		Assert.assertNotNull("Search should return at least one employee", results);
	}

	private Employee getEmployee(long employeeId, long departmentId, String firstName, String lastName, String birthDate, String gender, String hireDate) {
		Employee theEmployee = new Employee();
		theEmployee.setId(employeeId);
		theEmployee.setDepartmentId(departmentId);
		theEmployee.setFirstName(firstName);
		theEmployee.setLastName(lastName);
		theEmployee.setBirthDay(LocalDate.parse(birthDate, formatter));
		theEmployee.setGender(gender.charAt(0));
		theEmployee.setHireDate(LocalDate.parse(hireDate, formatter));
		return theEmployee;
	}
}
