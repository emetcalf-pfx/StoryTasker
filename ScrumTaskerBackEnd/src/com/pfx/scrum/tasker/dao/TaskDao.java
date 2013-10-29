package com.pfx.scrum.tasker.dao;

import java.util.Set;

import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;

public interface TaskDao {

	/**
	 * Creates task.
	 * 
	 * @param task
	 * @return Id of created task.
	 */
	int createTask(Task task);

	/**
	 * Returns task for given id.
	 * 
	 * @param id
	 * @return Task
	 */
	Task getTask(int id);
	
	/**
	 * Updates given task.
	 * 
	 * @param task
	 */
	void updateTask(Task task);

	/**
	 * Returns tasks for given story.
	 * 
	 * @return Set of tasks.
	 */
	Set<Task> getTasksForStory(Story story);
	
}
