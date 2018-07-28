package org.arnav.javabrains.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
//import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.arnav.javabrains.messenger.model.Message;
import org.arnav.javabrains.messenger.model.Profile;
import org.arnav.javabrains.messenger.resources.beans.MessageFilterBean;
import org.arnav.javabrains.messenger.service.MessageService;
//We can specify produces and Consumes at Class level or method level, the difference is Obvious :)

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) 

public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
//	public List<Message> getMessages(@QueryParam("year") int year, 
//									 @QueryParam("start") int start,
//									 @QueryParam("size") int size) {
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {	
//		return "Hello World"; Dummy rerurn just to check if service is working
		
//		if (year == 0 && size == 0 && start == 0) {
		if (filterBean.getYear() == 0 && filterBean.getSize() == 0 && filterBean.getStart() == 0) {
			System.out.println("in normal get list");
			return messageService.getAllMessages();
		}
		
		if (filterBean.getYear() > 0) {
			System.out.println("in year");
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0)	{
			System.out.println("in pagination");
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long Id,
							  @Context UriInfo uriInfo) {
//		return "Got Path Parm " + messageId;
		Message message =  messageService.getMessage(Id);
		
		//HATEOAS happening below
		message.addLink(uriInfo.getAbsolutePath().toString(), "self");
//		We can also can do it like below, this will wrongly add 2 self url's 
//		but for the understanding sake let it be
		String uriS = uriInfo.getBaseUriBuilder()  		//get base uri like http://localhost:8080/messenger/webapi/
				.path(MessageResource.class) 			//get messages resource /messages
				.path(Long.toString(message.getId()))	//get message id
				.build()								//build url
				.toString();							//convert to string
		message.addLink(uriS, "self");
		
		String uriC = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
//				.path("comments")
				.build()
				.toString();
		message.addLink(uriC, "comments");
		
		String uriP = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
		message.addLink(uriP, "profile");
		
		return message;
	}
	
	//Sending Location headers and custom status codes
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo)	{
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
				
//		Modified below to return the link to the resourse just created (Coded by Deepu)
//		return "/messages/" + message.getId();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message putMessage(@PathParam("messageId") long Id, Message message) {
		message.setId(Id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	//if you dont want a return type use "void" instead of "Message" type in below line
	public Message deleteMessage(@PathParam("messageId") long Id) {
		return messageService.removeMessage(Id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(@Context UriInfo uriInfo)	{
//		String str = uriInfo.getPath().toString();
//		return "Test - in '" + str + "' sub resource";
		return new CommentResource();
	}
}