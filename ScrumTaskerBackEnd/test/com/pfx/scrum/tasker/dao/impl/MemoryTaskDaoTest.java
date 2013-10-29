package com.pfx.scrum.tasker.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;
import com.pfx.scrum.tasker.model.User;

public class MemoryTaskDaoTest {
	private MemoryTaskDao classUnderTest;

	@Before
	public void setUp() throws Exception {
		classUnderTest = new MemoryTaskDao();
	}

	@Test
	public void createValidTask() {
		classUnderTest.createTask(getValidTask());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createTaskWithNoTitle() {
		classUnderTest.createTask(new Task());
	}

	@Test
	public void testGetTask() {
		Task task = getValidTask();
		int id = classUnderTest.createTask(task);
		assertEquals(task, classUnderTest.getTask(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTaskWithInvalidId() {
		classUnderTest.getTask(1);
	}

	@Test
	public void testUpdateEverythingInTask() {
		Task task = getValidTask();
		int id = classUnderTest.createTask(task);
		task = classUnderTest.getTask(id);
		task.setDescription("new description");
		task.setHours(8);
		task.setStory(new Story());
		task.setTitle("new title");
		task.setUser(new User());
		classUnderTest.updateTask(task);
		assertEquals(task, classUnderTest.getTask(id));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateStoryWithInvalidId() {
		Task task = getValidTask();
		task.setId(0);
		classUnderTest.updateTask(task);
	}

	@Test
	public void testGetTasksForStory() {
		Story story1 = new Story();
		story1.setTitle("story 1");
		Task story1Task1 = getValidTask();
		story1Task1.setStory(story1);
		Task story1Task2 = getValidTask();
		story1Task2.setStory(story1);

		Story story2 = new Story();
		story2.setTitle("story 2");
		Task story2Task1 = getValidTask();
		story2Task1.setStory(story2);

		classUnderTest.createTask(story1Task1);
		classUnderTest.createTask(story1Task2);
		classUnderTest.createTask(story2Task1);

		Set<Task> expectedStory1Tasks = new HashSet<Task>(2);
		expectedStory1Tasks.add(story1Task1);
		expectedStory1Tasks.add(story1Task2);
		assertEquals(expectedStory1Tasks,
				classUnderTest.getTasksForStory(story1));
	}

	private Task getValidTask() {
		Task task = new Task();
		task.setTitle("title");
		return task;
	}
}
