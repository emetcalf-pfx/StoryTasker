package com.pfx.scrum.tasker.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.pfx.scrum.tasker.model.Story;
import com.pfx.scrum.tasker.model.User;

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
		Story story = getValidStory();
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
		story.setHours(24);
		story.setPoints(8);
		story.setTitle("new title");
		story.setUser(new User());
		classUnderTest.updateStory(story);
		assertEquals(story, classUnderTest.getStory(id));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateStoryWithNoId() {
		classUnderTest.updateStory(getValidStory());
	}

	@Test
	public void testStoriesAreStored() {
		Set<Story> expectedStories = new HashSet<Story>();
		Story story1 = getValidStory();
		Story story2 = getValidStory();
		expectedStories.add(story1);
		expectedStories.add(story2);
		classUnderTest.createStory(story1);
		classUnderTest.createStory(story2);
		assertEquals(expectedStories, classUnderTest.getStories());
	}

	private Story getValidStory() {
		Story story = new Story();
		story.setTitle("title");
		return story;
	}

}
