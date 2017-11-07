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
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;

public class JDBCProjectIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCProjectDAO dao;
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
		dao = new JDBCProjectDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sqlGetEmployeeId = "SELECT nextval('seq_employee_id')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetEmployeeId);
		result.next();
		long id = result.getLong(1);
		
		testEmployee = getEmployee(id, 1, "John", "Smith", "1980-10-01", "M", "2017-10-03");
		
		String sqlInsertEmployee = "INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertEmployee, testEmployee.getId(), testEmployee.getDepartmentId(), testEmployee.getFirstName(), testEmployee.getLastName(), testEmployee.getBirthDay(), testEmployee.getGender(), testEmployee.getHireDate());
	}
	
	@After
	public void rollback () throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void save_new_project_and_read_it_back() throws SQLException{
		Project theProject = getProject("Project Blue", "2017-10-03");

		dao.save(theProject);
		
		List<Project> projects = dao.getAllActiveProjects();
		
		Assert.assertNotNull("The project should have an id", theProject.getId());
		Assert.assertTrue("There should be at least one project", projects.size() >= 1);
		Assert.assertTrue("The project we added should be in the list", projects.contains(theProject));
	}
	
	@Test
	public void get_all_active_projects_will_not_return_ended_projects() {
		Project theProject = getProject("Project Blue", "2017-09-03", "2017-09-13");
		dao.save(theProject);
		List<Project> projects = dao.getAllActiveProjects();
		
		Assert.assertNotNull("The project should have an id", theProject.getId());
		Assert.assertFalse("The project we added should NOT be in the list", projects.contains(theProject));
	}
	
	@Test
	public void add_employee_to_project() {
		Project theProject = getProject("Project Blue", "2017-09-03", "2017-09-13");
		dao.save(theProject);
		dao.addEmployeeToProject(theProject.getId(), testEmployee.getId());
		
		String sqlCheckEmployee = "SELECT * FROM project_employee WHERE employee_id = ? AND project_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCheckEmployee, testEmployee.getId(), theProject.getId());
		
		Assert.assertTrue("The result set should have an entry", result.next());
	}
	
	@Test
	public void remove_employee_to_project() {
		Project theProject = getProject("Project Blue", "2017-09-03", "2017-09-13");
		dao.save(theProject);
		dao.addEmployeeToProject(theProject.getId(), testEmployee.getId());
		dao.removeEmployeeFromProject(theProject.getId(), testEmployee.getId());
		
		String sqlCheckEmployee = "SELECT * FROM project_employee WHERE employee_id = ? AND project_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCheckEmployee, testEmployee.getId(), theProject.getId());
		
		Assert.assertFalse("The result set should NOT have an entry", result.next());
	}
	
	private Project getProject(String name, String fromDate) {
		Project theProject = new Project();
		theProject.setName(name);
		theProject.setStartDate(LocalDate.parse(fromDate, formatter));
		return theProject;
	}
	
	private Project getProject(String name, String fromDate, String toDate) {
		Project theProject = getProject(name, fromDate);
		theProject.setEndDate(LocalDate.parse(toDate, formatter));
		return theProject;
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
	
	private void assertProjectsAreEqual(Project expected, Project actual) {
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getStartDate(), actual.getStartDate());
		assertEquals(expected.getEndDate(), actual.getEndDate());
	}
}
