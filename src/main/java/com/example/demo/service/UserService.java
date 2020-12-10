package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.persistance.domain.User;
import com.example.demo.persistance.repo.UserRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class UserService {

	private UserRepo repo;

	private ModelMapper mapper;

	private UserDto mapToDTO(User user) {
		return this.mapper.map(user, UserDto.class);
	}

	@Autowired
	public UserService(UserRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public UserDto create(User user) {
		return this.mapToDTO(this.repo.save(user));
	}

	public List<UserDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());

	}

	public UserDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());

	}

	public UserDto update(UserDto userDto, Long id) {
		User toUpdate = this.repo.findById(id).orElseThrow();
		toUpdate.setName(userDto.getName());
		SpringBeanUtil.mergeNotNull(userDto, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));

	}

	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);// false because in js we can say if true and do something
	}

}
