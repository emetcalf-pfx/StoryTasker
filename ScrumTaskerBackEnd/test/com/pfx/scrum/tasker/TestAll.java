package com.pfx.scrum.tasker;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.pfx.scrum.tasker.dao.StoryDao;
import com.pfx.scrum.tasker.dao.UserDao;
import com.pfx.scrum.tasker.dao.impl.MemoryStoryDao;
import com.pfx.scrum.tasker.dao.impl.MemoryUserDao;
import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.Task;
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
		// Create stories.
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
		
		printSprint("Created 2 stories with no users.");
		
		// Create users.
		User user1 = new User();
		user1.setUsername("emetcalf");
		int userId1 = userDao.createUser(user1);
		User user2 = new User();
		user2.setUsername("ccapper");
		int userId2 = userDao.createUser(user2);

		// Add users to stories.
		story1.setUserId(userId1);
		storyDao.updateStory(story1);
		story2.setUserId(userId2);
		storyDao.updateStory(story2);
		
		printSprint("Added users to stories.");
		
		// Add task to story.
		Task story1Task1 = new Task();
		story1Task1.setUserId(story1.getUserId());
		story1Task1.setTitle("update design doc.");
		story1Task1.setHours(6);
		story1Task1.setStoryId(story1.getId());
		storyDao.addTask(story1Task1);
		
		printSprint("Added task to story 1.");
	}
	
	private void printSprint(String msg) {
		System.out.println("*** " + msg + " ***");
		for (Story story : storyDao.getStories()) {
			System.out.println(story);
			Set<Task> storyTasks = storyDao.getStoryTasks(story.getId());
			for (Task task: storyTasks) {
				System.out.println(task);
			}
		}
	}

}
