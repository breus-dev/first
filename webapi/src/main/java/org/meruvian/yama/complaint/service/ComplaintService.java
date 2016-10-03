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
import org.meruvian.yama.complaint.entity.Complaint;
import org.meruvian.yama.complaint.entity.Complaint.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Path("/api/complaints")
@Api("/complaints")
@Produces(MediaType.APPLICATION_JSON)
public interface ComplaintService {
	@GET
	@Path("/{id}")
	@ApiOperation(httpMethod = "GET", value = "GET complaint by ID", response = Complaint.class)
	Complaint getComplaintById(@PathParam("id") String id);
	
	@GET
	@ApiOperation(httpMethod = "GET", value = "Find complaint by name", response = Complaint.class, responseContainer = "Page")
	Page<Complaint> findComplaintByKeyword(@QueryParam("q") @DefaultValue("")
	String keyword, @QueryParam("status") Status status, Pageable pageable);
	
	@GET
	@Path("/{id}/comments")
	Page<Comment> findCommentsByComplaintId(@PathParam("id") String id, Pageable pageable);
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(httpMethod = "DELETE", value = "Delete complaint by ID")
	void removeComplaint(@PathParam("id") String id);
	
	@POST
	@ApiOperation(httpMethod = "POST", value = "Post complaint")
	Complaint saveComplaint(Complaint complaint);
	
	@PUT
	@Path("/{id}")
	@ApiOperation(httpMethod = "PUT", value = "PUT complaint by ID", response = Complaint.class)
	Complaint updateComplaint(@PathParam("id") String id, Complaint complaint);
	
	
	
	
	

}
