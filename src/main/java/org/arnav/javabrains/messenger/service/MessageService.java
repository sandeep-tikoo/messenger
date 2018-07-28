package org.arnav.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.arnav.javabrains.messenger.database.DatabaseClass;
import org.arnav.javabrains.messenger.exception.DataNotFoundException;
import org.arnav.javabrains.messenger.model.Message;

public class MessageService {

		
		private Map<Long, Message> messages = DatabaseClass.getMessages();
	
		public MessageService() {
			if (messages.size() < 1) {
				messages.put(1L, new Message(1, "Hello World!", "Arnav"));
				messages.put(2L, new Message(2, "Hello Jersey!", "Arnav"));
				messages.put(3L, new Message(3, "Hello Arnav!", "Deepu"));
				messages.put(4L, new Message(4, "Hello Arnav and Deepu!", "Sonu"));
			}
		}
		
		// GET Message Collection or List
		public List<Message> getAllMessages()	{
			return new ArrayList<Message>(messages.values());
//			Message m1 = new Message(1L, "Hello World!", "Arnav");
//			Message m2 = new Message(2L, "Hello World!", "Arnav");
//			List<Message> list = new ArrayList<>();
//			list.add(m1);
//			list.add(m2);
//			return list;
		}
		
		//Get for year, Query parm = year
		public List<Message> getAllMessagesForYear(int year)	{
			List<Message> messagesForYear = new ArrayList<>();
			Calendar cal = Calendar.getInstance();
			
			//Learn below for loop syntax seperately
			for (Message message : messages.values())	{
				cal.setTime(message.getCreated());
				if (cal.get(Calendar.YEAR)== year)	{
					messagesForYear.add(message);
				}
			}
			return messagesForYear;
		}
		
		// GET Message Collection paginated
		public List<Message> getAllMessagesPaginated(int start, int size)	{
			ArrayList <Message> list = new ArrayList<Message>(messages.values());
			
			if (start + size > list.size())	{ 
				return new ArrayList<Message>();
			}
			
			return list.subList(start, start + size);

		}
		
		// GET Message based on ID
		public Message getMessage(long id)	{
//			return messages.get(id);
			Message message = messages.get(id);
			if (message == null)	{
				throw new DataNotFoundException("Message with id " + id + " was not found");
			}
			return message;
		}
		
		// POST Add new Message
		public Message addMessage(Message message)	{
			message.setId(messages.size() + 1);
			messages.put(message.getId(), new Message(message.getId(), message.getAuthor(), message.getMessage()));
			return messages.get(message.getId());
			//Below failed effort to return resource locator URI
//			URI uri = uriInfo.getAbsolutePathBuilder().path(message.getId().toString()).build();
			//but his can be retrieved using @context annotation and pulling from HttpHeaders
		}
		
		// PUT Update Message
		public Message updateMessage(Message message) {
			if (message.getId() <= 0)	{
				return null;
			}
			messages.put(message.getId(), message);
			
			return message;
		}
		
		// DELETE Remove Message
		public Message removeMessage(long id)	{
			return messages.remove(id);
		}

}
