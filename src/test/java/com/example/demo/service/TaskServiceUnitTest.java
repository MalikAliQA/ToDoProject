package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.TaskDto;
import com.example.demo.persistance.domain.Task;
import com.example.demo.persistance.repo.TaskRepo;

@SpringBootTest
@ActiveProfiles(profiles = "testdb")
public class TaskServiceUnitTest {

	@Autowired
	private TaskService service;

	@MockBean
	private TaskRepo repo;

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

	@Test
	void createTest() throws Exception {
		when(this.repo.save(TEST_TASK_1)).thenReturn(TEST_TASK_1);
		assertThat(this.service.create(TEST_TASK_1)).isEqualTo(this.mapToDTO(TEST_TASK_1));
		verify(this.repo, atLeastOnce()).save(TEST_TASK_1);

	}

	@Test
	void readAllTest() throws Exception {
		when(this.repo.findAll()).thenReturn(LISTOFTASKS.stream().collect(Collectors.toList()));
		assertThat(this.service.readAll())
				.isEqualTo(LISTOFTASKS.stream().map(this::mapToDTO).collect(Collectors.toList()));
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void readOneTest() throws Exception {
		when(this.repo.findById(TEST_TASK_1.getId())).thenReturn(Optional.of(TEST_TASK_1));
		assertThat(this.service.readOne(TEST_TASK_1.getId())).isEqualTo(this.mapToDTO(TEST_TASK_1));
		verify(this.repo, atLeastOnce()).findById(TEST_TASK_1.getId());

	}

	@Test
	void updateTest() throws Exception {
		when(this.repo.findById(TEST_TASK_1.getId())).thenReturn(Optional.of(TEST_TASK_1));
		when(this.repo.save(TEST_TASK_1)).thenReturn(TEST_TASK_1);
		assertThat(this.service.update(mapToDTO(TEST_TASK_1), TEST_TASK_1.getId())).isEqualTo(mapToDTO(TEST_TASK_1));
		verify(this.repo, atLeastOnce()).save(TEST_TASK_1);

	}

	@Test
	void deleteTest() throws Exception {
		when(this.repo.existsById(TEST_TASK_1.getId())).thenReturn(false);
		assertThat(this.service.delete(TEST_TASK_1.getId())).isNotEqualTo(false);
		verify(this.repo, atLeastOnce()).existsById(TEST_TASK_1.getId());

	}

	@Test
	void deleteTestTwo() throws Exception {
		when(this.repo.existsById(TEST_TASK_1.getId())).thenReturn(true);
		assertThat(this.service.delete(TEST_TASK_1.getId())).isNotEqualTo(true);
		verify(this.repo, atLeastOnce()).existsById(TEST_TASK_1.getId());

	}

}
