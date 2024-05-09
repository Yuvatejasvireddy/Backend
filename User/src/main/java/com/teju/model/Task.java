package com.teju.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="task")
@Data

public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String image;
	private Long assigneduserid;
	private List<String> tags=new ArrayList<>();
	private LocalDateTime deadline;
	private LocalDateTime createdat;
	private String status;
	private String done;
	public String getDone() {
		return done;
	}
	public Task(Long id, String title, String description, String image, Long assigneduserid, List<String> tags,
			LocalDateTime deadline, LocalDateTime createdat, String status, String done) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.assigneduserid = assigneduserid;
		this.tags = tags;
		this.deadline = deadline;
		this.createdat = createdat;
		this.status = status;
		this.done = done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	
	public Task() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getAssigneduserid() {
		return assigneduserid;
	}
	public void setAssigneduserid(Long assigneduserid) {
		this.assigneduserid = assigneduserid;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public LocalDateTime getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	

	
	
	
}
