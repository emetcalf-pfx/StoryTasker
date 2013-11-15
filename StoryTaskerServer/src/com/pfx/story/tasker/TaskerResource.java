package com.pfx.story.tasker;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import com.pfx.scrum.tasker.model.User;

@Path("tasker")
public class TaskerResource extends Application {
	
	@GET
	@Path("/user/list")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<User> getUser() {
		List<User> users = new ArrayList<User>();
		users.add(getUser("ccapper"));
		users.add(getUser("emetcalf"));
		users.add(getUser("rob"));
		return users;
	}

	private User getUser(String username) {
		User user = new User();
		user.setUsername(username);
		return user;
	}

}
