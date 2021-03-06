package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.domain.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody User user) {
		UserDto created = this.service.create(user);
		return new ResponseEntity<>(created, HttpStatus.CREATED);

	}

	@GetMapping("/read")
	public ResponseEntity<List<UserDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<UserDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
		return new ResponseEntity<>(this.service.update(userDto, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				// no_content - if deleted successfully then should return nothing
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// if record isnt found internal server error

	}

}
