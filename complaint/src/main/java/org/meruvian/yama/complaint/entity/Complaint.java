package org.meruvian.yama.complaint.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.meruvian.yama.core.DefaultPersistence;
import org.meruvian.yama.core.user.User;

@Entity
@Table(name="yama_complaint")
public class Complaint extends DefaultPersistence {
	public enum Status {
		PUBLIC, PRIVATE
	}
	private String title;
	private String message;
	private Category category;
	private Status status;
	private User author;
	
	@NotBlank
	@Column( unique = true)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	
	@ManyToOne
	@JoinColumn(name="category_id")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
