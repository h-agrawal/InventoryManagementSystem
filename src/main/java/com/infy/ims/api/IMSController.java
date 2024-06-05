package com.infy.ims.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.ims.dto.TaskDTO;
import com.infy.ims.exception.InventoryManagementException;
import com.infy.ims.service.InventoryManagementService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/inventory")
public class IMSController {
	
	private static final Log LOGGER=LogFactory.getLog(IMSController.class);
	
	@Autowired
	private InventoryManagementService iMService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<TaskDTO>> getAllTask() throws InventoryManagementException{
		LOGGER.info("FEATCHING ALL ENTRIES");
		return ResponseEntity.ok(iMService.getAll());
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable @NotNull(message = "{ID.ABSENT}") Long id) throws InventoryManagementException{
		LOGGER.info("FEATCHING AN ENTRIE OF - "+id);
		return ResponseEntity.ok(iMService.getById(id));
	}
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody @Valid TaskDTO taskdto) throws InventoryManagementException{
		LOGGER.info("CREATING AN ENTRIE FOR - "+taskdto.getName());
		return ResponseEntity.ok(iMService.post(taskdto));
	}
	
	@PutMapping("/updatestatus/{id}")
	public ResponseEntity<TaskDTO> updateStatus(@PathVariable @NotNull(message = "{ID.ABSENT}") Long id)throws InventoryManagementException{
		LOGGER.info("UPDATING STATUS FOR TASK - "+id);
		return ResponseEntity.ok(iMService.updateStatus(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> delete(@PathVariable @NotNull(message = "{ID.ABSENT}") Long id) throws InventoryManagementException{
		LOGGER.info("DELETING ENTRY - "+id);
		return ResponseEntity.ok(iMService.delete(id));
	}

}
