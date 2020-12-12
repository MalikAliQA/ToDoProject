package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDto;
import com.example.demo.persistance.domain.Task;
import com.example.demo.persistance.repo.TaskRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class TaskService {

	// this is where our business logic will happen

	// this is also where CRUD logic will take place

	// repo is where CRUD functionality is implemented
	private TaskRepo repo;

	// makes obejct mapping easy by automatically determining how one object models
	private ModelMapper mapper;

	// create mapToDto
	private TaskDto mapToDTO(Task task) {
		return this.mapper.map(task, TaskDto.class);
	}

	@Autowired
	public TaskService(TaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create
	public TaskDto create(Task task) {
		return this.mapToDTO(this.repo.save(task));
	}

	// read all

	public List<TaskDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());

	}

	// read one method
	public TaskDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());

	}

	// update
	public TaskDto update(TaskDto taskDto, Long id) {
		// check if record exists
		Task toUpdate = this.repo.findById(id).orElseThrow();
		// set record to update
		toUpdate.setName(taskDto.getName());
		SpringBeanUtil.mergeNotNull(taskDto, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));

	}

	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);// false because in js we can say if true and do something
	}

}
