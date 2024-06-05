package com.infy.ims.service;

import java.util.List;

import com.infy.ims.dto.TaskDTO;
import com.infy.ims.exception.InventoryManagementException;

public interface InventoryManagementService {
	
	public List<TaskDTO> getAll() throws InventoryManagementException;
	public TaskDTO getById(Long id) throws InventoryManagementException;
	public Long post(TaskDTO taskDTO) throws InventoryManagementException;
	public TaskDTO updateStatus(Long id) throws InventoryManagementException;
	public Long delete(Long id) throws InventoryManagementException;

}
