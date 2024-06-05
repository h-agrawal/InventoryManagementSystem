package com.infy.ims.dto;

import jakarta.validation.constraints.NotNull;

public class TaskDTO {
	
	private Long id;
	@NotNull(message = "{name.absent}")
	private String name;
	@NotNull(message = "{description.absent}")
	private String description;
	private String status;
	
	public TaskDTO(Long id, String name, String description, String status) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
