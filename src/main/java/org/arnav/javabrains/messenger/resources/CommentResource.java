package org.arnav.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.arnav.javabrains.messenger.model.Comment;
import org.arnav.javabrains.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = new CommentService(); 

	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment)	{
		return commentService.addComment(messageId, comment);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, 
							  @PathParam("commentId") long commentId,
							  @Context UriInfo uriInfo) {
		return commentService.getComment(messageId, commentId, uriInfo.getAbsolutePath().toString());
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment putComment(@PathParam("messageId") long messageId,@PathParam("commentId") long id, Comment comment) {
		comment.setId(id);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment deleteComment(@PathParam("messageId") long messageId,@PathParam("commentId") long id) {
		return commentService.removeComment(messageId, id);
	}
	
	
//	@GET
//	public String test()	{
//		return "new sub resource";
//	}
//	
//	@GET
//	@Path("/{commentId}")
//	public String test1(@PathParam("messageId") long messageID,
//						@PathParam("commentId") long commentID)	{
//		return "Method to return Comment Id: " + commentID + " for message Id : " + messageID;
//	}
}
