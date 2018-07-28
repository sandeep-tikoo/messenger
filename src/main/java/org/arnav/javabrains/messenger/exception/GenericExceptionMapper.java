package org.arnav.javabrains.messenger.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.arnav.javabrains.messenger.model.ErrorMessage;

//@Provider
//@provider lets jax-rs knows there is a class which throws a custom exception and jax RS takes the object from here and 
//converts it into JSON
//it tells jersey to intercept this exception and take the error object and send that object as response.
//No matter what the exception is, just catch it, like a catch all
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	//Added code myself to also return the source url
	//uriInfo.getAbsolutePath().toString - in below method and @Context variable below
	private @Context UriInfo uriInfo;
	
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "please refer - https://javabrains.io/", uriInfo.getAbsolutePath().toString());
		return Response.status(Status.INTERNAL_SERVER_ERROR)
			.entity(errorMessage)
			.build();
	}
}