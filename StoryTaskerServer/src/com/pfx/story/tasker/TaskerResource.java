package com.pfx.story.tasker;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.pfx.scrum.tasker.dao.UserDao;
import com.pfx.scrum.tasker.dao.impl.MemoryUserDao;
import com.pfx.scrum.tasker.exception.DuplicateUsernameException;
import com.pfx.scrum.tasker.model.User;

@Path("tasker")
public class TaskerResource extends Application {
	// TODO: Inject
	private static UserDao userDao = new MemoryUserDao();
	
	@GET
	@Path("/users")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Set<User> getUser() {
		return userDao.getUsers();
	}
	
	@POST
	@Path("/users")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@NotNull
	public User createUser(User user) throws DuplicateUsernameException {
		// TODO: Handle empty username - bean validation?
		userDao.createUser(user);
		return user;
	}

}
