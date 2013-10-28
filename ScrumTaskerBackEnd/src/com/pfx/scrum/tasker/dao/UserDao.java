package com.pfx.scrum.tasker.dao;

import java.util.Set;

import com.pfx.scrum.tasker.exception.DuplicateUsernameException;
import com.pfx.scrum.tasker.model.User;

public interface UserDao {

	/**
	 * Adds user.
	 * 
	 * @param user
	 * @throws DuplicateUsernameException
	 *             is user with same username already exists.
	 */
	void createUser(User user) throws DuplicateUsernameException;

	/**
	 * Returns users.
	 * 
	 * @return Users
	 */
	Set<User> getUsers();

	/**
	 * Returns user given username.
	 * 
	 * @param username
	 * @return User
	 */
	User getUserByUsername(String username);
}
