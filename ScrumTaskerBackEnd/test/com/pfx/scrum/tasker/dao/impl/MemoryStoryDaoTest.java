package com.pfx.scrum.tasker.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;

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
	public void testGetStory() {
		Story story = new Story(getValidStory());
		int id = classUnderTest.createStory(story);
		assertEquals(story, classUnderTest.getStory(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetStoryWithInvalidId() {
		classUnderTest.getStory(0);
	}

	@Test
	public void testUpdateEverytingInStory() {
		Story story = getValidStory();
		int id = classUnderTest.createStory(story);
		story = classUnderTest.getStory(id);
		story.setDescription("new description");
		story.setPoints(8);
		story.setTitle("new title");
		story.setUserId(5);
		classUnderTest.updateStory(story);
		assertEquals(story, classUnderTest.getStory(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateStoryWithNullId() {
		Story story = getValidStory();
		classUnderTest.updateStory(story);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateStoryWithInvalidId() {
		Story story = getValidStory();
		story.setId(0);
		classUnderTest.updateStory(story);
	}

	@Test
	public void testStoriesAreStored() {
		Set<Story> expectedStories = new HashSet<Story>();
		Story story1 = getValidStory();
		Story story2 = getValidStory();
		expectedStories.add(new Story(story1));
		expectedStories.add(new Story(story2));
		classUnderTest.createStory(story1);
		classUnderTest.createStory(story2);
		assertEquals(expectedStories, classUnderTest.getStories());
	}

	@Test
	public void testAddingTasks() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task1 = getValidTask();
		task1.setStoryId(storyId);
		Task task2 = getValidTask();
		task2.setStoryId(storyId);
		classUnderTest.addTask(task1);
		classUnderTest.addTask(task2);
		Set<Task> expectedTasks = new HashSet<Task>();
		expectedTasks.add(task1);
		expectedTasks.add(task2);
		assertEquals(expectedTasks, classUnderTest.getStoryTasks(storyId));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddTaskWithNoStoryId() {
		Task task = getValidTask();
		classUnderTest.addTask(task);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddTaskWithNoExistingStoryId() {
		Task task = getValidTask();
		task.setStoryId(1);
		classUnderTest.addTask(task);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddTaskWithNoTitle() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task = new Task();
		task.setStoryId(storyId);
		classUnderTest.addTask(task);
	}

	@Test
	public void testUpdateTask() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task = getValidTask();
		task.setStoryId(storyId);
		int taskId = classUnderTest.addTask(task);
		Task updateTask = new Task();
		updateTask.setId(taskId);
		updateTask.setDescription("new description");
		updateTask.setHours(9);
		updateTask.setTitle("new title");
		updateTask.setUserId(5);
		updateTask.setStoryId(storyId);
		classUnderTest.updateTask(updateTask);
		Set<Task> expectedTasks = new HashSet<Task>(1);
		expectedTasks.add(updateTask);
		assertEquals(expectedTasks, classUnderTest.getStoryTasks(storyId));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTaskWithNullId() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task = getValidTask();
		task.setStoryId(storyId);
		classUnderTest.addTask(task);
		Task updateTask = new Task(task);
		classUnderTest.updateTask(updateTask);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTaskWithNullStoryId() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task = getValidTask();
		task.setStoryId(storyId);
		int taskId = classUnderTest.addTask(task);
		Task updateTask = getValidTask();
		updateTask.setId(taskId);
		classUnderTest.updateTask(updateTask);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTaskWithInvalidId() {
		Story story = getValidStory();
		int storyId = classUnderTest.createStory(story);
		Task task = getValidTask();
		task.setStoryId(storyId);
		int taskId = classUnderTest.addTask(task);
		task.setId(taskId + 1);
		classUnderTest.updateTask(task);
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
