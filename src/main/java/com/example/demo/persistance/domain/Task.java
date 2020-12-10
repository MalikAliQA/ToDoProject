package com.example.demo.persistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String body;

	@ManyToOne
	private User user;

	// constructor with all properties
	public Task(Long id, String name, String body) {
		super();
		this.id = id;
		this.name = name;
		this.body = body;
	}

	// constructor without ID because of AutoID feature
	public Task(String name, String body) {
		super();
		this.name = name;
		this.body = body;
	}

}
