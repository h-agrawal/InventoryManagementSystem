package com.infy.ims.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.ims.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
	
	public List<Task> findByName(String name);

}
