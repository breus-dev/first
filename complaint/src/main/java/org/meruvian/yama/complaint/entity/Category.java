package org.meruvian.yama.complaint.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.meruvian.yama.core.DefaultPersistence;

@Entity
@Table(name = "yama_category")
public class Category extends DefaultPersistence {
	
	private String name;
	private String description;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
