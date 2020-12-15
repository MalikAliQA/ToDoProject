package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "testdb")
public class UserControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	@Autowired
	private ModelMapper mapper;

	private UserDto mapToDTO(User user) {
		return this.mapper.map(user, UserDto.class);
	}

	// Test data
	private final User TEST_USER_1 = new User(1L, "Malik");
	private final User TEST_USER_2 = new User(2L, "Alice");

	// list
	private final List<User> LISTOFUSERS = List.of(TEST_USER_1, TEST_USER_2);

	private final String URL = "/user";

	// create
	@Test
	void createTest() throws Exception {
		UserDto testDTO = mapToDTO(new User("Bob"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		RequestBuilder request = post(URL + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isCreated();

		UserDto testSavedDTO = mapToDTO(new User("Bob"));
		testSavedDTO.setId(3L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	// readone
	@Test
	void readOneTest() throws Exception {

		RequestBuilder request = get(URL + "/read/1").contentType(MediaType.APPLICATION_JSON);// .content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json("{'id':1, 'name':'Malik'}");

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	// readall
	@Test
	void readAllTest() throws Exception {
		RequestBuilder request = get(URL + "/read").contentType(MediaType.APPLICATION_JSON);// .content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isOk();

		// ResultMatcher checkBody =

		this.mvc.perform(request).andExpect(checkStatus);// .andExpect(checkBody);

	}

	// delete
	@Test
	void deleteTest() throws Exception {
		RequestBuilder request = delete(URL + "/delete/2").contentType(MediaType.APPLICATION_JSON);// .content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(request).andExpect(checkStatus);// .andExpect(checkBody);

	}

	@Test
	void updateTest() throws Exception {
		UserDto testDTO = mapToDTO(new User("Bob"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		RequestBuilder request = put(URL + "/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isAccepted();

		// UserDto testSavedDTO = mapToDTO(new User("Bob"));
		// testSavedDTO.setId(1L);
		// String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json("{'id':1, 'name': 'Bob'}");// , 'tasks': [{}]}");

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

}
