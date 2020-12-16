package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.persistance.domain.User;

@SpringBootTest
@ActiveProfiles(profiles = "testdb")
public class UserUnitTest {

	@Test
	void argsconsturctTest() {
		new User().equals(new User());
	}

	@Test
	void hashcodetest() {
		User handle;

		handle = new User();
		assertEquals(357642, handle.hashCode());

	}

	@Test
	void tostringcodetest() {
		User handle;

		handle = new User();

		String expected = "User [id=" + null + ", name=" + null + ", tasks=" + null + "]";

		assertEquals(expected, handle.toString());

	}

}
