package com.pfx.scrum.tasker.model;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TaskedStoryTest {
	private TaskedStory taskedStory;

	@Before
	public void setUp() throws Exception {
		taskedStory = new TaskedStory(new Story());
	}
	
	@Test
	public void testTotalHours() {
		assertEquals(0, taskedStory.getTotalHours());
		
		Set<Task> tasks = new HashSet<Task>();
		Task task1 = new Task();
		int task1Hours = 5;
		task1.setHours(task1Hours);
		Task task2 = new Task();
		int task2Hours = 3;
		task2.setHours(task2Hours);
		tasks.add(task1);
		tasks.add(task2);
		taskedStory.setTasks(tasks);
		assertEquals(task1Hours + task2Hours, taskedStory.getTotalHours());
	}

}
