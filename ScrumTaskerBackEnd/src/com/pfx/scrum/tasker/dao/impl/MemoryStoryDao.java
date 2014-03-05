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
import com.pfx.scrum.tasker.model.Task;

public class MemoryStoryDao implements StoryDao {
	private static final AtomicInteger ID_GENERATOR = new AtomicInteger();

	private final Map<Integer, Story> storyById = new HashMap<Integer, Story>();
	private final Map<Integer, Task> taskById = new HashMap<Integer, Task>();
	private final Map<Integer, Set<Integer>> taskIdsByStoryId = new HashMap<Integer, Set<Integer>>();

	@Override
	public int createStory(Story story) {
		validateStory(story);
		Story newStory = new Story(story);
		int id = ID_GENERATOR.incrementAndGet();
		newStory.setId(id);
		storyById.put(id, newStory);
		return id;
	}

	@Override
	public Story getStory(int id) {
		return new Story(getStoryReference(id));
	}

	@Override
	public void updateStory(Story story) {
		if (story.getId() == null) {
			throw new IllegalArgumentException(String.format(
					"Story [%s] must have an id.", story));
		}
		validateStory(story);
		Story savedStory = getStoryReference(story.getId());
		savedStory.setTitle(story.getTitle());
		savedStory.setDescription(story.getDescription());
		savedStory.setUserId(story.getUserId());
		savedStory.setPoints(story.getPoints());
	}

	@Override
	public Set<Story> getStories() {
		Set<Story> copyOfStories = new HashSet<Story>(storyById.size());
		for (Story story : storyById.values()) {
			copyOfStories.add(new Story(story));
		}
		return Collections.unmodifiableSet(copyOfStories);
	}

	@Override
	public int addTask(Task task) {
		if (task.getStoryId() == null) {
			throw new IllegalArgumentException(String.format(
					"Task [%s] must have a story id.", task));
		}
		if (!storyById.containsKey(task.getStoryId())) {
			throw new IllegalArgumentException(String.format(
					"Task story %d does not exists", task.getStoryId()));
		}
		validateTask(task);
		Task newTask = new Task(task);
		int id = ID_GENERATOR.incrementAndGet();
		newTask.setId(id);
		taskById.put(id, newTask);
		Set<Integer> storyTaskIds = getTaskIds(newTask.getStoryId());
		storyTaskIds.add(id);
		taskIdsByStoryId.put(newTask.getStoryId(), storyTaskIds);
		return id;
	}

	@Override
	public void updateTask(Task task) {
		if (task.getId() == null) {
			throw new IllegalArgumentException(String.format(
					"Task [%s] must have an id.", task));
		}
		if (task.getStoryId() == null) {
			throw new IllegalArgumentException(String.format(
					"Task [%s] must have a story id.", task));
		}
		validateTask(task);
		Task savedTask = getTaskReference(task.getId());
		savedTask.setDescription(task.getDescription());
		savedTask.setHours(task.getHours());
		savedTask.setTitle(task.getTitle());
		savedTask.setUserId(task.getUserId());
		savedTask.setStoryId(task.getStoryId());
	}

	@Override
	public Set<Task> getStoryTasks(int storyId) {
		getStoryReference(storyId);
		Set<Task> tasks = new HashSet<Task>();
		for (int taskId : getTaskIds(storyId)) {
			tasks.add(taskById.get(taskId));
		}
		return tasks;
	}

	private void validateStory(Story story) {
		if (StringUtils.isEmpty(story.getTitle())) {
			throw new IllegalArgumentException("Story must have a title.");
		}
	}

	private void validateTask(Task task) {
		if (StringUtils.isEmpty(task.getTitle())) {
			throw new IllegalArgumentException("Task must have a title.");
		}
	}

	private Story getStoryReference(int id) {
		if (storyById.containsKey(id)) {
			return storyById.get(id);
		} else {
			throw new IllegalArgumentException(String.format(
					"Story with id [%d] does not exist.", id));
		}
	}

	private Task getTaskReference(int id) {
		if (taskById.containsKey(id)) {
			return taskById.get(id);
		} else {
			throw new IllegalArgumentException(String.format(
					"Task with id [%d] does not exist.", id));
		}
	}

	private Set<Integer> getTaskIds(int storyId) {
		if (taskIdsByStoryId.containsKey(storyId)) {
			return taskIdsByStoryId.get(storyId);
		} else {
			return new HashSet<Integer>();
		}
	}
}
