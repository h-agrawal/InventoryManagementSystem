package com.infy.ims;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ims.dto.TaskDTO;
import com.infy.ims.entity.Status;
import com.infy.ims.entity.Task;
import com.infy.ims.exception.InventoryManagementException;
import com.infy.ims.repository.TaskRepository;
import com.infy.ims.service.InventoryManagementService;
import com.infy.ims.service.InventoryManagementServiceImpl;

@SpringBootTest
class ImsApplicationTests {
	
	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private InventoryManagementService iMService = new InventoryManagementServiceImpl();
	
	@Test
	void testGetall() throws InventoryManagementException{
		List<Task> tasks = new ArrayList<>();
		Task task = new Task();
		task.setId(1l);
		task.setStatus(Status.PENDING);
		tasks.add(task);
		Mockito.when(taskRepository.findAll()).thenReturn(tasks);
		Assertions.assertEquals(1, iMService.getAll().get(0).getId()); 
	}
	
	@Test
	void testGetValid() throws InventoryManagementException{
		Task task = new Task();
		task.setId(1l);
		task.setStatus(Status.PENDING);
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(task));
		Assertions.assertEquals(1, iMService.getById(1l).getId()); 
	}
	
	@Test
	void testGetInvalid() throws InventoryManagementException{
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(InventoryManagementException.class, ()->iMService.getById(1l));
		Assertions.assertEquals("SERVICE.ID_NOT_PRESENT", e.getMessage());
	}
	
	@Test
	void testPostValid() throws InventoryManagementException{
		Mockito.when(taskRepository.findByName(Mockito.anyString())).thenReturn(new ArrayList<>());
		TaskDTO taskDTO = new TaskDTO(1l, "abc", null, null);
		Task task = new Task();
		task.setId(1l);
		Mockito.when(taskRepository.save(Mockito.any())).thenReturn(task);
		Assertions.assertEquals(1, iMService.post(taskDTO));
	}
	
	@Test
	void testPostInvalid() throws InventoryManagementException{
		List<Task> tasks = new ArrayList<>();
		Task task = new Task();
		tasks.add(task);
		TaskDTO taskDTO = new TaskDTO(null, "abc", null, null);
		Mockito.when(taskRepository.findByName(Mockito.anyString())).thenReturn(tasks);
		Exception e = Assertions.assertThrows(InventoryManagementException.class, ()->iMService.post(taskDTO));
		Assertions.assertEquals("SERVICE.TASK_ALREADY_PRESENT", e.getMessage());
	}
	
	@Test
	void testPutValid() throws InventoryManagementException{
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Task()));
		Assertions.assertEquals(1, iMService.updateStatus(1l).getId());
	}
	
	@Test
	void testPutInvalid() throws InventoryManagementException{
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(InventoryManagementException.class, ()->iMService.updateStatus(1l));
		Assertions.assertEquals("SERVICE.ID_NOT_PRESENT", e.getMessage());
	}
	
	@Test
	void testDeleteValid() throws InventoryManagementException{
		Task task =new Task();
		task.setId(1l);
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(task));
		Assertions.assertEquals(1, iMService.delete(1l));
	}
	
	@Test
	void testDeleteInvalid() throws InventoryManagementException{
		Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		Exception e = Assertions.assertThrows(InventoryManagementException.class, ()->iMService.delete(1l));
		Assertions.assertEquals("SERVICE.ID_NOT_PRESENT", e.getMessage());
	}

}
