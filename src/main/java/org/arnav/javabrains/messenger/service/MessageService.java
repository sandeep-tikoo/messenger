package org.arnav.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.arnav.javabrains.messenger.model.Message;

public class MessageService {

		public List<Message> getAllMessages()	{
			Message m1 = new Message(1L, "Hello World!", "Arnav");
			Message m2 = new Message(2L, "Hello World!", "Arnav");
			List<Message> list = new ArrayList<>();
			list.add(m1);
			list.add(m2);
			return list;
		}
}
