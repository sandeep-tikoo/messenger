package org.arnav.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.arnav.javabrains.messenger.database.DatabaseClass;
import org.arnav.javabrains.messenger.model.Comment;
import org.arnav.javabrains.messenger.model.Message;

public class CommentService {

private Map<Long, Message> messages = DatabaseClass.getMessages();
	
//	public CommentService() {
//		if (comments.size() < 1) {
//			comments.put(1L,  new Comment(1, "Hi Arnav, you are right", "Sandeep"));
//			comments.put(2L,  new Comment(2, "Hi Sandeep, How are you", "Kiran"));
//			comments.put(3L,  new Comment(3, "Hi Kiran, What's up", "Arnav"));
//		}
//	}
	
	public List<Comment> getAllComments(long messageId)	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId)	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	} 
	
	public Comment addComment(long messageId, Comment comment)	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
//		return comment;
		return comments.get(comment.getId());
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0)	{
			return null;
		}
		comments.put(comment.getId(), comment);
		
		return comment;
	}
	
	public Comment removeComment(Long messageId, Long commentId)	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
}
