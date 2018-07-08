package org.arnav.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.arnav.javabrains.messenger.database.DatabaseClass;
import org.arnav.javabrains.messenger.model.Message;

public class MessageService {

	
		private Map<Long, Message> messages = DatabaseClass.getMessages();
	
		public MessageService() {
			messages.put(1L, new Message(1, "Hello World!", "Arnav"));
			messages.put(2L, new Message(2, "Hello Jersey!", "Arnav"));
			messages.put(3L, new Message(3, "Hello Arnav!", "Deepu"));
			messages.put(4L, new Message(4, "Hello Arnav and Deepu!", "Sonu"));
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
		
		// GET Message based on ID
		public Message getMessage(long id)	{
			return messages.get(id);
		}
		
		// POST Add new Message
		public Message addMessage(Message message)	{
			message.setId(messages.size() + 1);
			messages.put(message.getId(), message);
			return message;
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
