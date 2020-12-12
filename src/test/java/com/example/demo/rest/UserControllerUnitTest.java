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

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.domain.User;
import com.example.demo.service.UserService;

@SpringBootTest
@ActiveProfiles("testdb")
public class UserControllerUnitTest {

	@Autowired
	private UserController controller;

	@MockBean
	private UserService service;

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

	// methods to test
	// create
	@Test
	void createTest() throws Exception {
		when(this.service.create(TEST_USER_1)).thenReturn(this.mapToDTO(TEST_USER_1));
		assertThat(new ResponseEntity<UserDto>(this.mapToDTO(TEST_USER_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_USER_1));
		verify(this.service, atLeastOnce()).create(TEST_USER_1);

	}

	// read one
	@Test
	void readOneTest() throws Exception {
		when(this.service.readOne(TEST_USER_1.getId())).thenReturn(this.mapToDTO(TEST_USER_1));
		assertThat(new ResponseEntity<UserDto>(this.mapToDTO(TEST_USER_1), HttpStatus.OK))
				.isEqualTo(this.controller.readOne(TEST_USER_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_USER_1.getId());
	}

	// read all
	@Test
	void readAllTest() throws Exception {
		when(this.service.readAll()).thenReturn(LISTOFUSERS.stream().map(this::mapToDTO).collect(Collectors.toList()));
		// assertThat(this.controller.read().getBody().isEmpty()).isFalse();
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(
				LISTOFUSERS.stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}

	// update
	@Test
	void updateTest() throws Exception {
		when(this.service.update(this.mapToDTO(TEST_USER_1), TEST_USER_1.getId()))
				.thenReturn(this.mapToDTO(TEST_USER_1));
		assertThat(new ResponseEntity<UserDto>(this.mapToDTO(TEST_USER_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(TEST_USER_1.getId(), this.mapToDTO(TEST_USER_1)));
		verify(this.service, atLeastOnce()).update(this.mapToDTO(TEST_USER_1), TEST_USER_1.getId());
	}

	// delete
	@Test
	void deleteTest() throws Exception {
		when(this.service.delete(TEST_USER_1.getId())).thenReturn(false);
		assertThat(this.controller.delete(TEST_USER_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.service, atLeastOnce()).delete(TEST_USER_1.getId());

	}

	@Test
	void deleteTestTwo() throws Exception {
		when(this.service.delete(TEST_USER_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_USER_1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_USER_1.getId());

	}

}
