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

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;

	public class JDBCDepartmentIntegrationTest {
		private static SingleConnectionDataSource dataSource;
		private JDBCDepartmentDAO dao;
		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		private JdbcTemplate jdbcTemplate;
		private Department testDepartment;
		
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
			dao = new JDBCDepartmentDAO(dataSource);
			jdbcTemplate = new JdbcTemplate(dataSource);
			
			testDepartment = dao.createDepartment("Fun");
		}

		@After
		public void rollback () throws SQLException {
			dataSource.getConnection().rollback();
		}
		
		@Test
		public void get_all_departments() {
			List<Department> results = dao.getAllDepartments();
			
			Assert.assertNotNull("The results should NOT be null", results);
			Assert.assertTrue("Search should return at least one department", results.size()>0);
		}
		
		@Test
		public void search_department_by_name() {
			List<Department> results = dao.searchDepartmentsByName("Fun");
			
			Assert.assertNotNull("Should find a department", results);
			Assert.assertTrue("Search should return at least one department", results.size()>0);
		}
		
		@Test
		public void update_department_name() {
			dao.updateDepartmentName(testDepartment.getId(), "Not Fun");
			
			List<Department> results = dao.searchDepartmentsByName("Not Fun");
			
			Assert.assertNotNull("Should find department with new name", results);
			Assert.assertTrue("Search should return at least one department", results.size()>0);
		}
		
		@Test
		public void get_department_by_id() {
			Department results = dao.getDepartmentById(testDepartment.getId());
			
			Assert.assertNotNull("Should find at least one department by id", results);
		}
}
