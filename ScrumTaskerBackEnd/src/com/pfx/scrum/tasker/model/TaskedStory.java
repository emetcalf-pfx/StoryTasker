package com.pfx.scrum.tasker.model;

import java.util.HashSet;
import java.util.Set;

public class TaskedStory extends Story {
	private Set<Task> tasks;

	/**
	 * Copy constructor.
	 * 
	 * @param story
	 */
	public TaskedStory(Story story) {
		super(story);
		this.tasks = new HashSet<Task>();
	}

	/**
	 * Copy constructor.
	 * 
	 * @param story
	 */
	public TaskedStory(TaskedStory taskedStory) {
		super(taskedStory);
		this.tasks = taskedStory.tasks;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	public int getNumTasks() {
		return tasks.size();
	}
	
	public int getTotalHours() {
		int hours = 0;
		for (Task task : tasks) {
			hours += task.getHours();
		}
		return hours;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskedStory other = (TaskedStory) obj;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskedStory [tasks=" + tasks + ", toString()="
				+ super.toString() + "]";
	}

}
