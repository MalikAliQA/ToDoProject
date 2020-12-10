package com.example.demo.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.persistance.domain.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	// allows us to implement
	// create
	// read
	// update
	// delete

	// any custom sql statments here

}
