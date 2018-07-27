package org.arnav.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.arnav.javabrains.messenger.model.Message;
import org.arnav.javabrains.messenger.service.MessageService;
//We can specify produces and Consumes at Class level or method level, the difference is Obvious :)

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@QueryParam("year") int year, 
									 @QueryParam("start") int start,
									 @QueryParam("size") int size) {
//		return "Hello World"; Dummy rerurn just to check if service is working
		
		if (year == 0 && size == 0 && start == 0) {
			System.out.println("in normal get list");
			return messageService.getAllMessages();
		}
		
		if (year > 0) {
			System.out.println("in year");
			return messageService.getAllMessagesForYear(year);
		}
		if (start >= 0 && size >= 0)	{
			System.out.println("in pagination");
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long Id) {
//		return "Got Path Parm " + messageId;
		return messageService.getMessage(Id);
	}
	
	@POST
	public Message addMessage(Message message)	{
		return messageService.addMessage(message);
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
}