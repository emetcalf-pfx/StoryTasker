package com.pfx.scrum.tasker;

import org.junit.Before;
import org.junit.Test;

import com.pfx.scrum.tasker.dao.StoryDao;
import com.pfx.scrum.tasker.dao.UserDao;
import com.pfx.scrum.tasker.dao.impl.MemoryStoryDao;
import com.pfx.scrum.tasker.dao.impl.MemoryUserDao;
import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;
import com.pfx.scrum.tasker.model.TaskedStory;
import com.pfx.scrum.tasker.model.User;

public class TestAll {
	private UserDao userDao;
	private StoryDao storyDao;

	@Before
	public void setUp() throws Exception {
		userDao = new MemoryUserDao();
		storyDao = new MemoryStoryDao();
	}
	
	@Test
	public void test() throws Exception {
		User user1 = new User();
		user1.setUsername("emetcalf");
		userDao.createUser(user1);
		User user2 = new User();
		user2.setUsername("ccapper");
		userDao.createUser(user2);
		
		Story story1 = new Story();
		story1.setTitle("Story 1");
		story1.setDescription("As a developer I want to do spring.");
		int id1 = storyDao.createStory(story1);
		story1.setId(id1);
		
		Story story2 = new Story();
		story2.setTitle("Story 2");
		story2.setDescription("I want to do something in the CRC.");
		int id2 = storyDao.createStory(story2);
		story2.setId(id2);
		
		for (TaskedStory taskedStory : storyDao.getTaskedStories()) {
			System.out.println(taskedStory);
			System.out.println("# tasks: " + taskedStory.getNumTasks());
			System.out.println("total hours: " + taskedStory.getTotalHours());
		}
		
		story1.setUser(user2);
		storyDao.updateStory(story1);
		story2.setUser(user1);
		storyDao.updateStory(story2);
		
		for (TaskedStory taskedStory : storyDao.getTaskedStories()) {
			System.out.println(taskedStory);
			System.out.println("# tasks: " + taskedStory.getNumTasks());
			System.out.println("total hours: " + taskedStory.getTotalHours());
		}
		
		Task story1Task1 = new Task();
		story1Task1.setUser(story1.getUser());
		story1Task1.setTitle("update design doc.");
		story1Task1.setHours(6);
		storyDao.addTask(story1.getId(), story1Task1);
		
		for (TaskedStory taskedStory : storyDao.getTaskedStories()) {
			System.out.println(taskedStory);
			System.out.println("# tasks: " + taskedStory.getNumTasks());
			System.out.println("total hours: " + taskedStory.getTotalHours());
		}
		
	}

}
