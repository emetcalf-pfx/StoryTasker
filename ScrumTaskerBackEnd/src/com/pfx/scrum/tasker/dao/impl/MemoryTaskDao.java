package com.pfx.scrum.tasker.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.pfx.scrum.tasker.dao.TaskDao;
import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;

public class MemoryTaskDao implements TaskDao {
	private static AtomicInteger ID_GENERATOR = new AtomicInteger();

	private final Map<Integer, Task> tasks = new HashMap<Integer, Task>();

	@Override
	public int createTask(Task task) {
		if (StringUtils.isEmpty(task.getTitle())) {
			throw new IllegalArgumentException("Task must have a title.");
		}
		Task newTask = new Task(task);
		int id = ID_GENERATOR.incrementAndGet();
		newTask.setId(id);
		tasks.put(id, newTask);
		return id;
	}

	@Override
	public Task getTask(int id) {
		return new Task(getTaskReference(id));
	}

	@Override
	public void updateTask(Task task) {
		getTaskReference(task.getId());
		tasks.put(task.getId(), task);
	}

	@Override
	public Set<Task> getTasksForStory(Story story) {
		Set<Task> storyTasks = new HashSet<Task>();
		for (Task task : tasks.values()) {
			if (story.equals(task.getStory())) {
				storyTasks.add(new Task(task));
			}
		}
		return Collections.unmodifiableSet(storyTasks);
	}

	private Task getTaskReference(int id) {
		if (tasks.containsKey(id)) {
			return tasks.get(id);
		} else {
			throw new IllegalArgumentException(String.format(
					"Task with id [%d] does not exist.", id));
		}
	}

}
