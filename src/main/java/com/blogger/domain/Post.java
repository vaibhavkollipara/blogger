package com.blogger.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="Post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq_generator")
	@SequenceGenerator(name="post_seq_generator", sequenceName = "post_seq", allocationSize=1001)
	@Column(name="POST_ID")
	private Long id;
	
	@Column(name="text")
	private String text;
	
	@Column(name="post_date")
	@Temporal(TemporalType.DATE)
	private Date post_date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	@JsonBackReference(value="user_post")
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="POST_COMMENT_MAP",
		joinColumns=@JoinColumn(name="POST_ID"),
		inverseJoinColumns=@JoinColumn(name="COMMENT_ID")
	)
	@JsonManagedReference(value="post_comment")
	@JsonProperty(access=Access.WRITE_ONLY)
	private List<Comment> commentsList = new ArrayList<Comment>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Comment> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comment> commentsList) {
		this.commentsList = commentsList;
	}

	@JsonProperty
	public String posted_by(){
		return this.user.getUsername();
	}
	
	@JsonProperty
	public int total_comments(){
		return this.commentsList.size();
	}
	
}
