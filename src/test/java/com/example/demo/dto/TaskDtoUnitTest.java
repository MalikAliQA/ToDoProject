package com.example.demo.dto;

import org.junit.jupiter.api.Test;

//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class TaskDtoUnitTest {

	@Test
	void argsconsturctTest() {
		new TaskDto().equals(new TaskDto());
	}

	@Test
	void hashcodetest() {
		TaskDto handle;

		handle = new TaskDto();
		handle.equals(handle);

		handle.hashCode();

	}

	@Test
	void tostringcodetest() {
		TaskDto handle;

		handle = new TaskDto();
		handle.equals(handle);

		handle.toString();

	}

}
