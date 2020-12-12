package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import com.example.demo.persistance.domain.User;

public class UserUnitTest {

	@Test
	void argsconsturctTest() {
		new User().equals(new User());
	}

	@Test
	void hashcodetest() {
		User handle;

		handle = new User();
		handle.equals(handle);

		handle.hashCode();

	}

	@Test
	void tostringcodetest() {
		User handle;

		handle = new User();
		handle.equals(handle);

		handle.toString();

	}

}
