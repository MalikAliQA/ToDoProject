package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.TaskDto;
import com.example.demo.persistance.domain.Task;
import com.example.demo.persistance.repo.TaskRepo;

@SpringBootTest
@ActiveProfiles(profiles = "testdb")
public class TaskServiceIntergrationTest {

	@Autowired
	private TaskService service;

	@Autowired
	private TaskRepo repo;

	@Autowired
	private ModelMapper mapper;

	private Task mapToPOJO(TaskDto task) {
		return this.mapper.map(task, Task.class);
	}

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

	@BeforeEach
	void setup() {
		this.repo.saveAll(LISTOFTASKS);
	}

	@Test
	void createTest() throws Exception {
		Task TEST_TASK_5 = new Task(5L, "shopping List", "oranges");
		assertThat(this.service.create(TEST_TASK_5)).isEqualTo(this.mapToDTO(TEST_TASK_5));
	}

	@Test
	void readAllTest() throws Exception {
		assertThat(this.service.readAll())
				.isEqualTo(LISTOFTASKS.stream().map(this::mapToDTO).collect(Collectors.toList()));
	}

	@Test
	void readOneTest() throws Exception {
		assertThat(this.service.readOne(TEST_TASK_1.getId())).isEqualTo(this.mapToDTO(TEST_TASK_1));
	}

	@Test
	void updateTest() throws Exception {
		Task TEST_TASK_5 = new Task(5L, "shopping List", "oranges");
		assertThat(this.service.update(this.mapToDTO(TEST_TASK_5), TEST_TASK_5.getId()))
				.isEqualTo(this.mapToDTO(TEST_TASK_5));

	}

//	@Test
//	void deleteTest() throws Exception {
//		assertThat(this.service.delete(TEST_TASK_1.getId())).isEqualTo(false);// .isEqualTo(null);
//	}

	@Test
	void deleteTestTwo() throws Exception {
		assertThat(this.service.delete(TEST_TASK_1.getId())).isEqualTo(true);// .isEqualTo(mapToDTO(TEST_TASK_1));
		// assertThat(this.service.delete(TEST_TASK_1.getId())).isEqualTo(TEST_TASK_1.getId());//
		// isEqualTo(true).isEqualTo(mapToDTO(TEST_TASK_1));
	}

}
