package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskDto;
import com.example.demo.persistance.domain.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	private TaskService service;

	@Autowired
	public TaskController(TaskService service) {
		super();
		this.service = service;
	}

	@GetMapping("hello") // this is the mapping i want - get me something
	public String hello() {
		return "hello from car";
	}

	// create method
	@PostMapping("/create")
	public ResponseEntity<TaskDto> create(@RequestBody Task task) {
		TaskDto created = this.service.create(task);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		// http status code 201 (created)

	}

	// read all
	@GetMapping("/read")
	public ResponseEntity<List<TaskDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
	}

	// read one
	@GetMapping("/read/{id}")
	public ResponseEntity<TaskDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDto> update(@PathVariable Long id, @RequestBody TaskDto taskDto) {
		return new ResponseEntity<>(this.service.update(taskDto, id), HttpStatus.ACCEPTED);
	}

	// delete one method
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TaskDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				// no_content - if deleted successfully then should return nothing
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// if record isnt found internal server error

	}

}
