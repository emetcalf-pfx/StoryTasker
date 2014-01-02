package com.pfx.story.tasker;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.pfx.scrum.tasker.exception.DuplicateUsernameException;

public class DuplicateUsernameExceptionMapper implements
		ExceptionMapper<DuplicateUsernameException> {

	@Context
	HttpHeaders headers;

	@Override
	public Response toResponse(DuplicateUsernameException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getMessage());
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(errorResponse)
				.type(headers.getAcceptableMediaTypes().get(0)).build();
	}

}
