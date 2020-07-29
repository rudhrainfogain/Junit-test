package com.employee.management.integration;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.employee.management.error.DepartmentErrorCodes;
import com.employee.management.exception.DepartmentException;
import com.employee.management.model.Department;
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
@ActiveProfiles("mock")
/*
 * To use Spring Mock MVC Test Framework, we need to use @AutoConfigureMockMvc
 * annotation.
 */
@AutoConfigureMockMvc
public class DepartmentIntegrationTest {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mvc;

	Department department, addedDepartment;
	List<Department> list;

	@Before
	public void setUp() {

		mvc = MockMvcBuilders.webAppContextSetup(context).alwaysDo(MockMvcResultHandlers.print()).build();
		department = new Department(1, "HR", "Human Resources Management");
		addedDepartment = new Department(7, "XYZ Deaprtment", "XYZ");
		list = new ArrayList<>();
		list.add(department);

	}
	/* Sample example for the GET request returning the single object */

	@Test
	public void testGetDepartment() {
		try {
			Department expectedDepartmentId = new Department(10, "XYZ Departemnt", "XYZ");
			ResultActions resultActions = mvc.perform(get("/departments/{id}", 10)).andExpect(status().isOk())
					.andExpect(content().contentType((MediaType.APPLICATION_JSON_UTF8)));
			resultActions.andExpect(status().isOk());
			ObjectMapper mapper = new ObjectMapper();
			// JSON from file to Object
			Department department = mapper.readValue(resultActions.andReturn().getResponse().getContentAsString(),
					new TypeReference<Department>() {
					});
			assertEquals(expectedDepartmentId.getDepartment_ID(), department.getDepartment_ID());
			assertEquals(expectedDepartmentId.getDepartment_Name(), department.getDepartment_Name());
			assertEquals(expectedDepartmentId.getShort_Name(), department.getShort_Name());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	/*
	 * Sample example to test the EXCEPTION when we try to fetch the department by
	 * providing the negative department id
	 */

	@Test
	public void testNegativeDepartmentIdException() {
		DepartmentErrorCodes expectedErrorCode = DepartmentErrorCodes.DEPARTMENT_ID_EXCEPTION;
		try {
			MvcResult result = mvc.perform(get("/departments/{id}", -1)).andReturn();
			Optional<DepartmentException> departmentExcepton = Optional
					.ofNullable((DepartmentException) result.getResolvedException());
			departmentExcepton.ifPresent((se) -> assertThat(se, is(notNullValue())));
			departmentExcepton.ifPresent((se) -> assertThat(se, is(instanceOf(DepartmentException.class))));
			assertEquals(expectedErrorCode.getMessage(), result.getResolvedException().getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/* Sample example for the POST request */
	@Test
	public void testAddDepartment() {
		try {
			mvc.perform(MockMvcRequestBuilders.post("/adddepartments").content(getJsonFromRequest(addedDepartment))
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	/*
	 * Sample example to test the EXCEPTION when we try to add the department by
	 * providing the empty department name
	 */

	@Test
	public void testAddDepartmentException() {
		DepartmentErrorCodes expectedErrorCode = DepartmentErrorCodes.DEPARTMENT_NAME_EXCEPTION;
		addedDepartment.setDepartment_Name("");
		try {
			MvcResult result = mvc
					.perform(MockMvcRequestBuilders.post("/adddepartments").content(getJsonFromRequest(addedDepartment))
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest()).andReturn();
			result.getResponse().getContentAsString();
			assertEquals(expectedErrorCode.getMessage(), result.getResolvedException().getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	/* Sample example for the GET request returning the List */

	@Test
	public void testGetAllDepartment() {
		try {
			List<Department> expectedlist = new ArrayList<>();
			expectedlist.add(new Department(10, "XYZ Departemnt", "XYZ"));
			ResultActions resultActions = mvc.perform(get("/departments")).andExpect(status().isOk())
					.andExpect(content().contentType((MediaType.APPLICATION_JSON_UTF8)));
			resultActions.andExpect(status().isOk());
			ObjectMapper mapper = new ObjectMapper();
			// JSON from file to Object
			List<Department> departmentlist = mapper.readValue(
					resultActions.andReturn().getResponse().getContentAsString(),
					new TypeReference<List<Department>>() {
					});
			assertEquals(expectedlist.size(), departmentlist.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	/* Sample example for the DELETE request */

	@Test
	public void deleteDepartement() throws Exception {
		mvc.perform(delete("/departments")).andExpect(status().isOk());
	}

	/* Sample example for the DELETE request and passing path parameter */
	@Test
	public void deleteDepartementById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/departments/{id}", 1).content(getJsonFromRequest(department))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/* Sample example for the PUT request and passing path parameter */
	@Test
	public void updateDepartment() throws Exception {
		department.setDepartment_Name("Finance Department");
		department.setShort_Name("FINANCE");
		mvc.perform(MockMvcRequestBuilders.put("/departments/{id}", 1).content(getJsonFromRequest(department))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	private String getJsonFromRequest(Department department) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(department);
	}
}
