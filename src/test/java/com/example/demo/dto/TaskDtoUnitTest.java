package com.example.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

		System.out.println(handle.hashCode());
		assertEquals(357642, handle.hashCode());

	}

	@Test
	void tostringcodetest() {
		TaskDto handle;

		handle = new TaskDto();

		String expected = "TaskDto [id=" + null + ", name=" + null + ", body=" + null + "]";

		assertEquals(expected, handle.toString());

	}

}
