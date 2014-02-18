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
import com.pfx.scrum.tasker.model.TaskedStory;

public class MemoryStoryDao implements StoryDao {
	private static AtomicInteger ID_GENERATOR = new AtomicInteger();

	private final Map<Integer, TaskedStory> taskedStories = new HashMap<Integer, TaskedStory>();

	@Override
	public int createStory(Story story) {
		if (StringUtils.isEmpty(story.getTitle())) {
			throw new IllegalArgumentException("Story title must be populated.");
		}
		TaskedStory newTaskedStory = new TaskedStory(story);
		int id = ID_GENERATOR.incrementAndGet();
		newTaskedStory.setId(id);
		taskedStories.put(id, newTaskedStory);
		return id;
	}

	@Override
	public TaskedStory getTaskedStory(int id) {
		return new TaskedStory(getStoryReference(id));
	}

	@Override
	public void updateStory(Story story) {
		TaskedStory savedTaskedStory = getStoryReference(story.getId());
		TaskedStory taskedStory = new TaskedStory(story);
		taskedStory.setTasks(savedTaskedStory.getTasks());
		taskedStories.put(story.getId(), taskedStory);
	}

	@Override
	public Set<TaskedStory> getTaskedStories() {
		Set<TaskedStory> copyTaskedStories = new HashSet<TaskedStory>(
				taskedStories.size());
		for (TaskedStory taskedStory : taskedStories.values()) {
			copyTaskedStories.add(new TaskedStory(taskedStory));
		}
		return Collections.unmodifiableSet(copyTaskedStories);
	}

	@Override
	public int addTask(int storyId, Task task) {
		if (StringUtils.isEmpty(task.getTitle())) {
			throw new IllegalArgumentException("Task must have a title.");
		}
		Task newTask = new Task(task);
		int id = ID_GENERATOR.incrementAndGet();
		newTask.setId(id);
		getStoryReference(storyId).getTasks().add(newTask);
		return id;
	}

	@Override
	public void updateTask(int storyId, Task task) {
		TaskedStory taskedStory = getStoryReference(storyId);
		Task savedTask = getTask(taskedStory, task.getId());
		savedTask.setDescription(task.getDescription());
		savedTask.setHours(task.getHours());
		savedTask.setTitle(task.getTitle());
		savedTask.setUserId(task.getUserId());
	}

	private TaskedStory getStoryReference(int id) {
		if (taskedStories.containsKey(id)) {
			return taskedStories.get(id);
		} else {
			throw new IllegalArgumentException(String.format(
					"Story with id [%d] does not exist.", id));
		}
	}

	private Task getTask(TaskedStory taskedStory, int taskId) {
		for (Task task : taskedStory.getTasks()) {
			if (task.getId() == taskId) {
				return task;
			}
		}
		throw new IllegalArgumentException(String.format(
				"Task with id [%d] does not exist.", taskId));
	}

}
