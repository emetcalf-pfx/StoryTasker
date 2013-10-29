package com.pfx.scrum.tasker.dao;

import java.util.Set;

import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;
import com.pfx.scrum.tasker.model.TaskedStory;

public interface StoryDao {
	
	/**
	 * Creates story
	 * 
	 * @param story
	 * @return Id of story.
	 */
	int createStory(Story story);
	
	/**
	 * Returns story given id.
	 * 
	 * @param id
	 * @return Tasked story
	 */
	TaskedStory getTaskedStory(int id);
	
	/**
	 * Update story to given values.
	 * 
	 * @param story
	 */
	void updateStory(Story story);
	
	/**
	 * Returns all stories.
	 * 
	 * @return Set of tasked stories
	 */
	Set<TaskedStory> getTaskedStories();
	
	/**
	 * Adds task to story
	 * 
	 * @param story id
	 * @param task
	 * @return Id of created task.
	 */
	int addTask(int storyId, Task task);
	
	/**
	 * Updates given task.
	 * 
	 * @param story id
	 * @param task
	 */
	void updateTask(int storyId, Task task);
	
}
