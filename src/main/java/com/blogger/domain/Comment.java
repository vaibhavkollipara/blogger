package com.blogger.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="Comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_generator")
	@SequenceGenerator(name="comment_seq_generator", sequenceName = "comment_seq", allocationSize=10001)
	@Column(name="COMMENT_ID")
	private Long id;
	
	@Column(name="comment")
	private String comment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	@JsonProperty(access=Access.WRITE_ONLY)
	@JsonBackReference(value="user_comment")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="postId")
	@JsonProperty(access=Access.WRITE_ONLY)
	@JsonBackReference(value="post_comment")
	private Post post;
	
	@Column(name="comment_date")
	@Temporal(TemporalType.DATE)
	private Date commented_on;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getCommented_on() {
		return commented_on;
	}

	public void setCommented_on(Date commented_on) {
		this.commented_on = commented_on;
	}
	
	@JsonProperty
	public Long parent_post(){
		return this.post.getId();
	}
	
	@JsonProperty
	public String comment_by(){
		return this.user.getUsername();
	}
	
	
	
	

}
