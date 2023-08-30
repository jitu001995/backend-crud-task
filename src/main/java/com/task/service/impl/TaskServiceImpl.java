package com.task.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.dto.TaskDto;
import com.task.exception.ResourceNotFoundException;
import com.task.model.Task;
import com.task.repo.TaskRepository;
import com.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
     @Autowired
     private TaskRepository taskRepo;
     
     @Autowired
     private ModelMapper modelMapper;
	
	@Override
	public TaskDto addTask(TaskDto taskDto) {
		Task task=modelMapper.map(taskDto, Task.class);
		return modelMapper.map(taskRepo.save(task),TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTask() {
		List<Task> taskList=taskRepo.findAll();
		return taskList.stream().map(task-> modelMapper.map(task,TaskDto.class)).toList();
	}

	@Override
	public TaskDto getTaskById(Integer id) {
		Optional<Task> task=taskRepo.findById(id);
		if(task.isPresent()) {
			Task taskDataById=task.get();
			return modelMapper.map(taskDataById, TaskDto.class);
		}else {
			throw new ResourceNotFoundException("Task could not find with Given Id");
		}
	}

	@Override
	public TaskDto updateTaskById(Integer id, TaskDto taskDto) {
		Optional<Task> task=taskRepo.findById(id);
		if(task.isPresent()) {
			taskDto.setId(id);
			Task newTask=modelMapper.map(taskDto, Task.class);
			return modelMapper.map(taskRepo.save(newTask), TaskDto.class);
		}else {
			throw new ResourceNotFoundException("Task could not find with Given Id");
		}
	}

	@Override
	public Boolean deleteTaskById(Integer id) {
		Optional<Task> task=taskRepo.findById(id);
		if(task.isPresent()) {
			taskRepo.deleteById(id);
			return true;
		}else {
			throw new ResourceNotFoundException("Task could not find with Given Id");
		}
	}

}
