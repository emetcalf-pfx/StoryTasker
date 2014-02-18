package com.pfx.scrum.tasker.model;

public class Task {
	private int id;
	private String title;
	private String description;
	private int hours = 0;
	private Integer userId;
	private int storyId;

	/**
	 * Default constructor.
	 */
	public Task() {
	}

	/**
	 * Copy constructor.
	 * 
	 * @param task
	 */
	public Task(Task task) {
		this.id = task.id;
		this.title = task.title;
		this.description = task.description;
		this.hours = task.hours;
		this.userId = task.userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getStoryId() {
		return storyId;
	}

	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + hours;
		result = prime * result + storyId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hours != other.hours)
			return false;
		if (storyId != other.storyId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description="
				+ description + ", hours=" + hours + ", userId=" + userId
				+ ", storyId=" + storyId + "]";
	}

}
