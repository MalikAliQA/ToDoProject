package com.example.demo.dto;

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
		handle.equals(handle);

		handle.toString();

	}

}
