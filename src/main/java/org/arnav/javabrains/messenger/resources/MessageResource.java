package org.arnav.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.arnav.javabrains.messenger.model.Message;
import org.arnav.javabrains.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
//	public String getMessages() {
	public List<Message> getMessages() {
//		return "Hello World";
		return messageService.getAllMessages();
	}
	
//	@POST
//	public String postMessages()	{
//		
//	}
}
