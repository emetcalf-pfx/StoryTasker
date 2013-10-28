package com.pfx.scrum.tasker.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.pfx.scrum.tasker.exception.DuplicateUsernameException;
import com.pfx.scrum.tasker.model.User;

public class MemoryUserDaoTest {
	private int incrementer = 0;
	private MemoryUserDao classUnderTest;

	@Before
	public void setUp() throws Exception {
		classUnderTest = new MemoryUserDao();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateUserWithNoUsername() throws Exception {
		classUnderTest.createUser(new User());
	}

	@Test(expected = DuplicateUsernameException.class)
	public void testCreateUserWithDuplicateUsername() throws Exception {
		User user = new User();
		user.setUsername("same username");
		classUnderTest.createUser(user);
		classUnderTest.createUser(user);
	}

	@Test
	public void testUsersAreStored() throws Exception {
		Set<User> expectedUsers = new HashSet<User>();
		User user1 = getValidUser();
		User user2 = getValidUser();
		expectedUsers.add(user1);
		expectedUsers.add(user2);
		classUnderTest.createUser(user1);
		classUnderTest.createUser(user2);
		assertEquals(expectedUsers, classUnderTest.getUsers());
	}

	@Test
	public void testGetValidUser() throws Exception {
		User user = getValidUser();
		classUnderTest.createUser(user);
		assertEquals(user, classUnderTest.getUserByUsername(user.getUsername()));
	}

	private User getValidUser() {
		User user = new User();
		user.setUsername("new username" + incrementer++);
		return user;
	}

}
