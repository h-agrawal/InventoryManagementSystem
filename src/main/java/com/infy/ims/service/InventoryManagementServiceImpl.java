package com.infy.ims.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.ims.dto.TaskDTO;
import com.infy.ims.entity.Status;
import com.infy.ims.entity.Task;
import com.infy.ims.exception.InventoryManagementException;
import com.infy.ims.repository.TaskRepository;

@Service
@Transactional
public class InventoryManagementServiceImpl implements InventoryManagementService{

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	public List<TaskDTO> getAll() throws InventoryManagementException {
		Iterable<Task> tasks = taskRepository.findAll();
		List<TaskDTO> taskDTOs = new ArrayList<>();
		tasks.forEach(task->taskDTOs.add(new TaskDTO(task.getId(),task.getName(),task.getDescription(),task.getStatus().toString())));
		return taskDTOs;
	}

	@Override
	public TaskDTO getById(Long id) throws InventoryManagementException {
		Task task= taskRepository.findById(id).orElseThrow(()->new InventoryManagementException("SERVICE.ID_NOT_PRESENT"));
		
		return new TaskDTO(id, task.getName(), task.getDescription(), task.getStatus().toString());
	}

	@Override
	public Long post(TaskDTO taskDTO) throws InventoryManagementException {
		if(taskRepository.findByName(taskDTO.getName()).isEmpty()) {
		Task task = new Task();
		task.setDescription(taskDTO.getDescription());
		task.setName(taskDTO.getName());
		task.setStatus(Status.PENDING);
		return taskRepository.save(task).getId();
		}
		throw new InventoryManagementException("SERVICE.TASK_ALREADY_PRESENT");
		
	}

	@Override
	public TaskDTO updateStatus(Long id) throws InventoryManagementException {
		Task task= taskRepository.findById(id).orElseThrow(()->new InventoryManagementException("SERVICE.ID_NOT_PRESENT"));
		task.setStatus(Status.COMPLETED);
		taskRepository.save(task);
		return new TaskDTO(id, task.getName(), task.getDescription(), task.getStatus().toString());
	}

	@Override
	public Long delete(Long id) throws InventoryManagementException {
		Task task= taskRepository.findById(id).orElseThrow(()->new InventoryManagementException("SERVICE.ID_NOT_PRESENT"));
		taskRepository.delete(task);
		return task.getId();
	}

	
}
