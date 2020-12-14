package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UserDtoUnitTest {

	@Test
	void argsconsturctTest() {
		new UserDto().equals(new UserDto());
	}

	@Test
	void hashcodetest() {
		UserDto handle;

		handle = new UserDto();
		handle.equals(handle);

		handle.hashCode();

	}

	@Test
	void tostringcodetest() {
		UserDto handle;

		handle = new UserDto();
		// handle.equals(handle);
		String expected = "UserDto [id=" + null + ", name=" + null + ", tasks=" + new ArrayList<>() + "]";
		// handle.toString();
		assertEquals(expected, handle.toString());

	}

}
