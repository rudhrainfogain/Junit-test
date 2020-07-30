package com.employee.management.integration;

/*This file contains the integration test cases that directly interact with the test database*/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
/*
 * The @SpringBootTest annotation is used to specify the application
 * configuration to load before running the tests
 */
@SpringBootTest
/*
 * To use Spring Mock MVC Test Framework, we need to use @AutoConfigureMockMvc
 * annotation.
 */
@AutoConfigureMockMvc
public class DepartmentIntegrationWithDatabaseTest {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private MockMvc mvc;

	Department department, addedDepartment;
	List<Department> list;

	@Before
	public void setUp() {

		mvc = MockMvcBuilders.webAppContextSetup(context).alwaysDo(MockMvcResultHandlers.print()).build();
		department = new Department(1, "HR", "Human Resources Management");
		addedDepartment = new Department(1, "XYZ Deaprtment", "XYZ");
		list = new ArrayList<>();
		list.add(department);

	}

	/*
	 * Sample example for the POST request adding the department in the H2
	 * Testdatabase and fetching that particular added department by MockMvc GET
	 * Request
	 */
	@Test
	public void testAddDepartment() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/adddepartments").content(getJsonFromRequest(addedDepartment))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			ResultActions resultActions = mvc.perform(get("/departments/{id}", 1)).andExpect(status().isOk())
					.andExpect(content().contentType((MediaType.APPLICATION_JSON_UTF8)));
			resultActions.andExpect(status().isOk());
			ObjectMapper mapper = new ObjectMapper();
			// JSON from file to Object
			Department department = mapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
					new TypeReference<Department>() {
					});
			assertEquals(addedDepartment.getDepartment_ID(), department.getDepartment_ID());
			assertEquals(addedDepartment.getDepartment_Name(), department.getDepartment_Name());
			assertEquals(addedDepartment.getShort_Name(), department.getShort_Name());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/*
	 * Sample example for the POST request adding the department in the H2 database
	 * and fetching that particular added department using repository findById
	 * method
	 */
	@Test
	public void testaddDeaprtmentWithRepository() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/adddepartments").content(getJsonFromRequest(addedDepartment))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

			// Calling the findById method of the repository to fetch the added data from
			// the database
			Department department = departmentRepository.findById(1).get();
			assertEquals(addedDepartment.getDepartment_ID(), department.getDepartment_ID());
			assertEquals(addedDepartment.getDepartment_Name(), department.getDepartment_Name());
			assertEquals(addedDepartment.getShort_Name(), department.getShort_Name());

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private String getJsonFromRequest(Department department) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(department);
	}
}
