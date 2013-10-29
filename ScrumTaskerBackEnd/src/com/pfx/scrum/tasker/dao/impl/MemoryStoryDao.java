package com.pfx.scrum.tasker.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.pfx.scrum.tasker.dao.StoryDao;
import com.pfx.scrum.tasker.model.Story;

public class MemoryStoryDao implements StoryDao {
	private static AtomicInteger ID_GENERATOR = new AtomicInteger();

	private final Map<Integer, Story> stories = new HashMap<Integer, Story>();

	@Override
	public int createStory(Story story) {
		if (StringUtils.isEmpty(story.getTitle())) {
			throw new IllegalArgumentException("Story title must be populated.");
		}
		Story newStory = new Story(story);
		int id = ID_GENERATOR.incrementAndGet();
		newStory.setId(id);
		stories.put(id, newStory);
		return id;
	}

	@Override
	public Story getStory(int id) {
		return new Story(getStoryReference(id));
	}

	@Override
	public void updateStory(Story story) {
		getStoryReference(story.getId());
		stories.put(story.getId(), story);
	}

	@Override
	public Set<Story> getStories() {
		Set<Story> copyStories = new HashSet<Story>(stories.size());
		for (Story story : stories.values()) {
			copyStories.add(new Story(story));
		}
		return Collections.unmodifiableSet(copyStories);
	}

	private Story getStoryReference(int id) {
		if (stories.containsKey(id)) {
			return stories.get(id);
		} else {
			throw new IllegalArgumentException(String.format(
					"Story with id [%d] does not exist.", id));
		}
	}

}
