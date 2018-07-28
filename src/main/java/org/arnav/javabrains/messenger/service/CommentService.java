package org.arnav.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.arnav.javabrains.messenger.database.DatabaseClass;
import org.arnav.javabrains.messenger.model.Comment;
import org.arnav.javabrains.messenger.model.ErrorMessage;
import org.arnav.javabrains.messenger.model.Message;

public class CommentService {

private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	@Context UriInfo uriInfo;

	public List<Comment> getAllComments(long messageId)	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
// Commented below to test Using WebApplicationException, see how object creation is seperated and object is checked for null	
//	public Comment getComment(long messageId, long commentId)	{
//		Map<Long, Comment> comments = messages.get(messageId).getComments();
//		return comments.get(commentId);
//	} 
	
	public Comment getComment(long messageId, long commentId, String uriA)	{
		
		System.out.println("In Comments instence");
		ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "please refer - https://javabrains.io/", uriA);
		System.out.println("In Comments instence1");
		//response builder below to set status code and set payload as  errorMessage object
		Response response = Response.status(Status.NOT_FOUND)
		.entity(errorMessage)
		.build();
		System.out.println("In Comments instence2");
		Message message = messages.get(messageId);
		if (message == null)	{
			System.out.println("In Comments instence, but message id is null");
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			System.out.println("In Comments instence, but comment id is null");
			//Another example to send not found, much cleaner/specific way than WebApplicationException
			//See Java docs for more specific error classes / methods
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	
	public Comment addComment(long messageId, Comment comment)	{
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
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
