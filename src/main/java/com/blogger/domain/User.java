package com.blogger.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="User_DETAILS")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
	@SequenceGenerator(name="user_seq_generator", sequenceName = "user_seq", allocationSize=101)
	@Column(name="USER_ID")
	private Long id;
	
	@NotNull
	@NotBlank
	@Column(unique=true)
	private String username;
	
	@NotNull
	@NotBlank
	@Column(unique=true)
	private String email;
	
	@NotNull
	@NotBlank
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String firstName;
	
	private String LastName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="USER_POST_MAP",
			joinColumns=@JoinColumn(name="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="POST_ID"))
	@JsonManagedReference(value="user_post")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<Post> postsList = new HashSet<Post>();
	
	public User(){
		
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Set<Post> getPostsList() {
		return postsList;
	}
	
	@JsonProperty(access=Access.READ_ONLY)
	public void setPostsList(Set<Post> posts) {
		postsList=posts;
	}
	
	
	

}
