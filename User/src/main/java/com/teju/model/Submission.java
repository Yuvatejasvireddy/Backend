package com.teju.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="submission")
public class Submission {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	Long taskid;
	String githublink;
	Long userId;
	public Submission() {
		super();
	}
	public Submission(Long id, Long taskid, String githublink, Long userId, String status,
			LocalDateTime submissiontime) {
		super();
		this.id = id;
		this.taskid = taskid;
		this.githublink = githublink;
		this.userId = userId;
		this.status = status;
		this.submissiontime = submissiontime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTaskid() {
		return taskid;
	}
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	public String getGithublink() {
		return githublink;
	}
	public void setGithublink(String githublink) {
		this.githublink = githublink;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getSubmissiontime() {
		return submissiontime;
	}
	public void setSubmissiontime(LocalDateTime submissiontime) {
		this.submissiontime = submissiontime;
	}
	String status="pending";
	LocalDateTime submissiontime;
	
	
	
	
}
