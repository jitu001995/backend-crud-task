package com.task.service;

import java.util.List;

import com.task.dto.TaskDto;

public interface TaskService {
   
	TaskDto addTask(TaskDto taskDto);
	List<TaskDto> getAllTask();
	TaskDto getTaskById(Integer id);
	TaskDto updateTaskById(Integer id,TaskDto taskDto);
	Boolean deleteTaskById(Integer id);
}
