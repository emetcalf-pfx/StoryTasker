package com.pfx.scrum.tasker.dao.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.pfx.scrum.tasker.dao.StoryDao;
import com.pfx.scrum.tasker.model.Story;

public class MemoryStoryDao implements StoryDao {
	private static AtomicInteger idGenerator = new AtomicInteger();

	private final Set<Story> stories = new HashSet<Story>();

	@Override
	public int createStory(Story story) {
		if (StringUtils.isEmpty(story.getTitle())) {
			throw new IllegalArgumentException("Story title must be populated.");
		}
		Story newStory = new Story(story);
		newStory.setId(idGenerator.incrementAndGet());
		stories.add(newStory);
		return newStory.getId();
	}

	@Override
	public Story getStory(int id) {
		for (Story story : stories) {
			if (story.getId() == id) {
				return new Story(story);
			}
		}
		throw new IllegalArgumentException(String.format(
				"Story with id [%d] does not exist.", id));
	}

	@Override
	public void updateStory(Story story) {
		if (story.getId() == null) {
			throw new IllegalArgumentException(String.format(
					"Story [%s] must have id to update.", story));
		}
		stories.add(story);
	}

	@Override
	public Set<Story> getStories() {
		Set<Story> copyStories = new HashSet<Story>(stories.size());
		for (Story story : stories) {
			copyStories.add(new Story(story));
		}
		return Collections.unmodifiableSet(copyStories);
	}

}
