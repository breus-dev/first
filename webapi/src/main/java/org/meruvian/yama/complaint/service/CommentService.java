package org.meruvian.yama.complaint.service;


import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.meruvian.yama.complaint.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Path("/api/comments")
@Api("/comments")
@Produces(MediaType.APPLICATION_JSON)
public interface CommentService {
	@GET
	@Path("/{id}")
	@ApiOperation(httpMethod = "GET", value = "GET comment by ID"
			+ "", response = Comment.class)
	Comment getCommentById(@PathParam("id") String id);
	
	@GET
	@ApiOperation(httpMethod = "GET", value = "Find comment by name"
			+ "", response = Comment.class, responseContainer = "Page")
	Page<Comment> findCommentByKeyword(@QueryParam("q") @DefaultValue("")
	String keyword, Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(httpMethod = "DELETE", value = "Delete comment by ID")
	void removeComment(@PathParam("id") String id);
	
	@POST
	@ApiOperation(httpMethod = "POST", value = "Post comment")
	Comment saveComment(Comment comment);
	
	@PUT
	@Path("/{id}")
	@ApiOperation(httpMethod = "PUT", value = "PUT comment by ID"
			+ "", response = Comment.class)
	Comment updateComment(@PathParam("id") String id, Comment comment);

	Comment getComplaintById(String id);
	
	@GET
	@Path("/complaint/{id}")
	@ApiOperation(httpMethod = "GET", value = "Find comment by complaint id"
			+ "", response = Comment.class, responseContainer = "Page")
	Page<Comment> getCommentByComplaintId(@PathParam("id") String id, Pageable pageable);

}