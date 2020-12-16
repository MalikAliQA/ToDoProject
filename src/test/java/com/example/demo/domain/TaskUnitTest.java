package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.persistance.domain.Task;

@SpringBootTest
@ActiveProfiles(profiles = "testdb")
public class TaskUnitTest {

	@Test
	void argsconsturctTest() {
		new Task().equals(new Task());
	}

	@Test
	void hashcodetest() {
		Task handle;
		handle = new Task(1L, "Malik", "random");

		// System.out.println(handle.hashCode());
		assertEquals(759919770, handle.hashCode());

	}

	@Test
	void tostringcodetest() {
		Task handle;

		handle = new Task(1L, "Malik", "random");

		String expected = "Task [id=" + 1L + ", name=" + "Malik" + ", body=" + "random" + ", user=" + null + "]";
		assertEquals(expected, handle.toString());
	}

}
