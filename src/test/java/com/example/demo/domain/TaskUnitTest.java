package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.demo.persistance.domain.Task;

public class TaskUnitTest {

	@Test
	void argsconsturctTest() {
		new Task().equals(new Task());
	}

	@Test
	void hashcodetest() {
		Task handle;

		handle = new Task();
		handle.equals(handle);

		handle.hashCode();

	}

	@Test
	void tostringcodetest() {
		Task handle;

		handle = new Task(1L, "Malik", "random");
		// handle.equals(handle);

		// handle.toString();
		String expected = "Task [id=" + 1L + ", name=" + "Malik" + ", body=" + "random" + ", user=" + null + "]";
		assertEquals(expected, handle.toString());
	}

}
