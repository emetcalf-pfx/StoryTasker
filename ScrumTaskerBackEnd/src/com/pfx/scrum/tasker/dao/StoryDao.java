package com.pfx.scrum.tasker.dao;

import java.util.Set;

import com.pfx.scrum.tasker.model.Story;

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
}
