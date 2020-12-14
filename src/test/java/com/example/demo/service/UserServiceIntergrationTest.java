package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.domain.User;
import com.example.demo.persistance.repo.UserRepo;

@SpringBootTest
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "testdb")
public class UserServiceIntergrationTest {

	@Autowired
	private UserService service;

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper mapper;

	private User mapToPOJO(UserDto user) {
		return this.mapper.map(user, User.class);
	}

	private UserDto mapToDTO(User user) {
		return this.mapper.map(user, UserDto.class);
	}

	// Test data
	private final User TEST_USER_1 = new User(1L, "Malik", new ArrayList<>());
	private final User TEST_USER_2 = new User(2L, "Alice", new ArrayList<>());

	// list
	private final List<User> LISTOFUSERS = List.of(TEST_USER_1, TEST_USER_2);

	@BeforeEach
	void setup() {
		this.repo.saveAll(LISTOFUSERS);
	}

	@Test
	void createTest() throws Exception {
		User TEST_USER_3 = new User(3L, "Bob");
		assertThat(this.service.create(TEST_USER_3)).isEqualTo(this.mapToDTO(TEST_USER_3));
	}

	@Test
	void readAllTest() throws Exception {
		assertThat(this.service.readAll().stream().map(this::mapToPOJO)).isEqualTo(LISTOFUSERS);
	}

	@Test
	void readOneTest() throws Exception {
		assertThat(this.service.readOne(TEST_USER_1.getId())).isEqualTo(this.mapToDTO(TEST_USER_1));
	}

	@Test
	void updateTest() throws Exception {
		User TEST_USER_2 = new User(2L, "Bob", new ArrayList<>());
		assertThat(this.service.update(mapToDTO(TEST_USER_2), TEST_USER_2.getId()))
				.isEqualTo(this.mapToDTO(TEST_USER_2));

	}

//	@Test
//	void deleteTest() throws Exception {
//		assertThat(this.service.delete(TEST_USER_1.getId())).isEqualTo(false).isEqualTo(null);
//	}

	@Test
	void deleteTestTwo() throws Exception {
		assertThat(this.service.delete(TEST_USER_1.getId())).isEqualTo(true);// .isEqualTo(mapToDTO(TEST_TASK_1));
		// assertThat(this.service.delete(TEST_TASK_1.getId())).isEqualTo(TEST_TASK_1.getId());//
		// isEqualTo(true).isEqualTo(mapToDTO(TEST_TASK_1));
	}

}
