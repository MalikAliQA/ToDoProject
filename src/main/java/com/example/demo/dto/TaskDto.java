package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {

	private Long id;
	private String name;
	private String body;

	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", name=" + name + ", body=" + body + "]";
	}

}
