package com.pfx.scrum.tasker.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;
import com.pfx.scrum.tasker.model.TaskedStory;

public class MemoryStoryDaoTest {
	private MemoryStoryDao classUnderTest;

	@Before
	public void setUp() throws Exception {
		classUnderTest = new MemoryStoryDao();
	}

	@Test
	public void testValidCreateStory() {
		classUnderTest.createStory(getValidStory());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateStoryWithNoTitle() {
		Story story = new Story();
		classUnderTest.createStory(story);
	}

	@Test
	public void testTaskedGetStory() {
		TaskedStory story = new TaskedStory(getValidStory());
		int id = classUnderTest.createStory(story);
		assertEquals(story, classUnderTest.getTaskedStory(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetStoryWithInvalidId() {
		classUnderTest.getTaskedStory(0);
	}

	@Test
	public void testUpdateEverytingInStory() {
		Story story = getValidStory();
		int id = classUnderTest.createStory(story);
		story = classUnderTest.getTaskedStory(id);
		story.setDescription("new description");
		story.setPoints(8);
		story.setTitle("new title");
		story.setUserId(5);
		classUnderTest.updateStory(story);
		assertEquals(story, classUnderTest.getTaskedStory(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateStoryWithInvalidId() {
		Story story = getValidStory();
		story.setId(0);
		classUnderTest.updateStory(story);
	}

	@Test
	public void testStoriesAreStored() {
		Set<TaskedStory> expectedTaskedStories = new HashSet<TaskedStory>();
		Story story1 = getValidStory();
		Story story2 = getValidStory();
		expectedTaskedStories.add(new TaskedStory(story1));
		expectedTaskedStories.add(new TaskedStory(story2));
		classUnderTest.createStory(story1);
		classUnderTest.createStory(story2);
		assertEquals(expectedTaskedStories, classUnderTest.getTaskedStories());
	}

	@Test
	public void testAddingTasks() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task1 = getValidTask();
		Task task2 = getValidTask();
		classUnderTest.addTask(storyId, task1);
		classUnderTest.addTask(storyId, task2);
		Set<Task> expectedTasks = new HashSet<Task>();
		expectedTasks.add(task1);
		expectedTasks.add(task2);
		assertEquals(expectedTasks, classUnderTest.getTaskedStory(storyId)
				.getTasks());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddTaskWithNoTitle() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task = new Task();
		classUnderTest.addTask(storyId, task);
	}

	@Test
	public void testUpdateTask() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		int taskId = classUnderTest.addTask(storyId, getValidTask());
		Task updateTask = new Task();
		updateTask.setId(taskId);
		updateTask.setDescription("new description");
		updateTask.setHours(9);
		updateTask.setTitle("new title");
		updateTask.setUserId(5);
		classUnderTest.updateTask(storyId, updateTask);
		Set<Task> expectedTasks = new HashSet<Task>(1);
		expectedTasks.add(updateTask);
		assertEquals(expectedTasks, classUnderTest.getTaskedStory(storyId)
				.getTasks());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTaskWithInvalidId() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		int taskId = classUnderTest.addTask(storyId, getValidTask());
		Task updateTask = new Task();
		updateTask.setId(taskId + 1);
		classUnderTest.updateTask(storyId, updateTask);
	}

	private Story getValidStory() {
		Story story = new Story();
		story.setTitle("title");
		return story;
	}

	private Task getValidTask() {
		Task task = new Task();
		task.setTitle("title");
		return task;
	}

}
