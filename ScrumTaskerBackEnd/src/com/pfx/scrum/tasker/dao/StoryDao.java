package com.pfx.scrum.tasker.dao;

import java.util.Set;

import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;

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
	 * @return Story
	 */
	Story getStory(int id);

	/**
	 * Update story to given values.
	 * 
	 * @param story
	 */
	void updateStory(Story story);

	/**
	 * Returns all stories.
	 * 
	 * @return Set of stories
	 */
	Set<Story> getStories();

	/**
	 * Adds task to story
	 * 
	 * @param task
	 * @return Id of created task.
	 */
	int addTask(Task task);

	/**
	 * Updates given task.
	 * 
	 * @param task
	 */
	void updateTask(Task task);

	/**
	 * Returns tasks for given story.
	 * 
	 * @param story
	 *            id
	 * @return Set of tasks.
	 */
	Set<Task> getStoryTasks(int storyId);

}
