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

import com.example.demo.dto.TaskDto;
import com.example.demo.persistance.domain.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "testdb")
public class TaskControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	@Autowired
	private ModelMapper mapper;

	private TaskDto mapToDTO(Task task) {
		return this.mapper.map(task, TaskDto.class);
	}

	// Test data
	private final Task TEST_TASK_1 = new Task(1L, "shopping List", "apples");
	private final Task TEST_TASK_2 = new Task(2L, "shopping List", "bananas");
	private final Task TEST_TASK_3 = new Task(3L, "shopping List", "carrots");
	private final Task TEST_TASK_4 = new Task(4L, "shopping List", "donuts");

	// list
	private final List<Task> LISTOFTASKS = List.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3, TEST_TASK_4);

	private final String URL = "/task";

	// create
	@Test
	void createTest() throws Exception {
		TaskDto testDTO = mapToDTO(new Task("Shopping List", "Oranges"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		RequestBuilder request = post(URL + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isCreated();

		TaskDto testSavedDTO = mapToDTO(new Task("Shopping List", "Oranges"));
		testSavedDTO.setId(5L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	// readone
	@Test
	void readOneTest() throws Exception {

		RequestBuilder request = get(URL + "/read/1").contentType(MediaType.APPLICATION_JSON);// .content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json("{'id':1, 'name': 'shopping List', 'body': 'apples'}");

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
		TaskDto testDTO = mapToDTO(new Task("Shopping List", "pizza"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		RequestBuilder request = put(URL + "/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isAccepted();

		TaskDto testSavedDTO = mapToDTO(new Task("Shopping List", "pizza"));
		testSavedDTO.setId(1L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}
}
