package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.TaskDto;
import com.task.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {
   
	@Autowired
	private TaskService service;
	
	@PostMapping("/")
	public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto){
		TaskDto savedTaskDto = service.addTask(taskDto);
		return new ResponseEntity<>(savedTaskDto,HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<TaskDto>> getAllTask(){
		List<TaskDto> taskList=service.getAllTask();
		return new ResponseEntity<>(taskList,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Integer id){
		TaskDto taskDto=service.getTaskById(id);
		return new ResponseEntity<>(taskDto,HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<TaskDto> updateTaskById(@PathVariable("id")Integer id,@RequestBody TaskDto taskDto){
		TaskDto updatedTaskDto=service.updateTaskById(id,taskDto);
		return new ResponseEntity<>(updatedTaskDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable("id")Integer id){
		boolean isTrue=service.deleteTaskById(id);
		if(isTrue) {
		   return new ResponseEntity<>("Deleted is successfully",HttpStatus.OK);
		}else {
			 return new ResponseEntity<>("Deleted is not successfully",HttpStatus.OK);
		}
		
	}
}
