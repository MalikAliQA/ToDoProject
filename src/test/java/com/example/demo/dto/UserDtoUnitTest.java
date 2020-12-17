package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UserDtoUnitTest {

	@Test
	void argsconsturctequalsTest() {
		new UserDto().equals(new UserDto());
		assertEquals(true, (new UserDto().equals(new UserDto())));
	}

	@Test
	void hashcodetest() {
		UserDto handle;

		handle = new UserDto();

		System.out.println(handle.hashCode());
		assertEquals(357600, handle.hashCode());

	}

	@Test
	void tostringcodetest() {
		UserDto handle;

		handle = new UserDto();

		String expected = "UserDto [id=" + null + ", name=" + null + ", tasks=" + new ArrayList<>() + "]";

		assertEquals(expected, handle.toString());

	}

}
