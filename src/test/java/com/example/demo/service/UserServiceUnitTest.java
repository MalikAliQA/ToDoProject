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

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.domain.User;
import com.example.demo.persistance.repo.UserRepo;

@SpringBootTest
@ActiveProfiles(profiles = "testdb")
public class UserServiceUnitTest {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepo repo;

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

	@Test
	void createTest() throws Exception {
		when(this.repo.save(TEST_USER_1)).thenReturn(TEST_USER_1);
		assertThat(this.service.create(TEST_USER_1)).isEqualTo(this.mapToDTO(TEST_USER_1));
		verify(this.repo, atLeastOnce()).save(TEST_USER_1);

	}

	@Test
	void readAllTest() throws Exception {
		when(this.repo.findAll()).thenReturn(LISTOFUSERS.stream().collect(Collectors.toList()));
		assertThat(this.service.readAll())
				.isEqualTo(LISTOFUSERS.stream().map(this::mapToDTO).collect(Collectors.toList()));
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void readOneTest() throws Exception {
		when(this.repo.findById(TEST_USER_1.getId())).thenReturn(Optional.of(TEST_USER_1));
		assertThat(this.service.readOne(TEST_USER_1.getId())).isEqualTo(this.mapToDTO(TEST_USER_1));
		verify(this.repo, atLeastOnce()).findById(TEST_USER_1.getId());

	}

	@Test
	void updateTest() throws Exception {
		when(this.repo.findById(TEST_USER_1.getId())).thenReturn(Optional.of(TEST_USER_1));
		when(this.repo.save(TEST_USER_1)).thenReturn(TEST_USER_1);
		assertThat(this.service.update(mapToDTO(TEST_USER_1), TEST_USER_1.getId())).isEqualTo(mapToDTO(TEST_USER_1));
		verify(this.repo, atLeastOnce()).save(TEST_USER_1);

	}

	@Test
	void deleteTest() throws Exception {
		when(this.repo.existsById(TEST_USER_1.getId())).thenReturn(false);
		assertThat(this.service.delete(TEST_USER_1.getId())).isNotEqualTo(false);
		verify(this.repo, atLeastOnce()).existsById(TEST_USER_1.getId());

	}

	@Test
	void deleteTestTwo() throws Exception {
		when(this.repo.existsById(TEST_USER_1.getId())).thenReturn(true);
		assertThat(this.service.delete(TEST_USER_1.getId())).isNotEqualTo(true);
		verify(this.repo, atLeastOnce()).existsById(TEST_USER_1.getId());

	}

}
