package org.meruvian.yama.complaint.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultPersistence;
import org.meruvian.yama.core.user.User;

@Entity
@Table(name = "yama_comment")
public class Comment extends DefaultPersistence {
	
	private Complaint complaint;
	private String title;
	private String comment;
	private User author;
	
	@ManyToOne
	@JoinColumn(name ="complaint_id")
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
