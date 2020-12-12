package com.example.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.TaskDto;
import com.example.demo.persistance.domain.Task;
import com.example.demo.service.TaskService;

@SpringBootTest
@ActiveProfiles("testdb")
public class TaskControllerUnitTest {

	@Autowired
	private TaskController controller;

	@MockBean
	private TaskService service;

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

	// methods to test
	// create
	@Test
	void createTest() throws Exception {
		when(this.service.create(TEST_TASK_1)).thenReturn(this.mapToDTO(TEST_TASK_1));
		assertThat(new ResponseEntity<TaskDto>(this.mapToDTO(TEST_TASK_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_TASK_1));
		verify(this.service, atLeastOnce()).create(TEST_TASK_1);

	}

	// read one
	@Test
	void readOneTest() throws Exception {
		when(this.service.readOne(TEST_TASK_1.getId())).thenReturn(this.mapToDTO(TEST_TASK_1));
		assertThat(new ResponseEntity<TaskDto>(this.mapToDTO(TEST_TASK_1), HttpStatus.OK))
				.isEqualTo(this.controller.readOne(TEST_TASK_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_TASK_1.getId());
	}

	// read all
	@Test
	void readAllTest() throws Exception {
		when(this.service.readAll()).thenReturn(LISTOFTASKS.stream().map(this::mapToDTO).collect(Collectors.toList()));
		// assertThat(this.controller.read().getBody().isEmpty()).isFalse();
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(
				LISTOFTASKS.stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}

	// update
	@Test
	void updateTest() throws Exception {
		when(this.service.update(this.mapToDTO(TEST_TASK_1), TEST_TASK_1.getId()))
				.thenReturn(this.mapToDTO(TEST_TASK_1));
		assertThat(new ResponseEntity<TaskDto>(this.mapToDTO(TEST_TASK_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(TEST_TASK_1.getId(), this.mapToDTO(TEST_TASK_1)));
		verify(this.service, atLeastOnce()).update(this.mapToDTO(TEST_TASK_1), TEST_TASK_1.getId());
	}

	// delete
	@Test
	void deleteTest() throws Exception {
		when(this.service.delete(TEST_TASK_1.getId())).thenReturn(false);
		assertThat(this.controller.delete(TEST_TASK_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.service, atLeastOnce()).delete(TEST_TASK_1.getId());

	}

	@Test
	void deleteTestTwo() throws Exception {
		when(this.service.delete(TEST_TASK_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_TASK_1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_TASK_1.getId());

	}

}
