package com.pfx.scrum.tasker.dao.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.pfx.scrum.tasker.dao.UserDao;
import com.pfx.scrum.tasker.exception.DuplicateUsernameException;
import com.pfx.scrum.tasker.model.User;

public class MemoryUserDao implements UserDao {
	private static final AtomicInteger ID_GENERATOR = new AtomicInteger();
	
	private final Set<User> users = new HashSet<User>();

	@Override
	public int createUser(User user) throws DuplicateUsernameException {
		if (StringUtils.isEmpty(user.getUsername())) {
			throw new IllegalArgumentException("User must have username.");
		}
		if (getUserByUsername(user.getUsername()).getUsername() != null) {
			throw new DuplicateUsernameException(String.format(
					"'%s' already exists.", user.getUsername()));
		}
		User newUser = new User(user);
		int id = ID_GENERATOR.incrementAndGet();
		newUser.setId(id);
		users.add(newUser);
		return id;
	}

	@Override
	public Set<User> getUsers() {
		Set<User> copyUsers = new HashSet<User>(users.size());
		for (User user : users) {
			copyUsers.add(user);
		}
		return Collections.unmodifiableSet(copyUsers);
	}

	@Override
	public User getUserByUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return new User();
	}

}
