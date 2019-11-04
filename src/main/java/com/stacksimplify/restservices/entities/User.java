package com.stacksimplify.restservices.entities;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.hateoas.ResourceSupport;

@ApiModel("This is user management service")
@Entity
@Table(name = "user")
public class User extends ResourceSupport {
	
	@ApiModelProperty(notes="Auto generated",required=true,position=1)
	@Id
	@GeneratedValue
	private long userid;
	
	@ApiModelProperty(notes="Auto generated",required=false,position=2)
	@Size(min=2,max=40)
	@NotEmpty(message="Username Cannot Be Left Blank")
	@Column(name="USER_NAME",length=50,nullable=false,unique=true)
	private String username;
	
	@Size(min=2,max=40,message="Length cannot Be Less Than 2")
	@Column(name="FIRST_NAME",length=50,nullable=false)
	private String firstname;
	
	@Column(name="LAST_NAME",length=50,nullable=false)
	private String lastname;
	
	@Column(name="EMAIL",length=50,nullable=false)
	private String email;
	
	@Column(name="ROLE",length=50,nullable=false)
	private String role;
	
	@Column(name="SSN",length=50,nullable=false,unique=true)
    private String ssn;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	@Column(name="ADDRESS")
	private String address;
	
	public User() {
		
	}

	

	public User(long userid, @NotEmpty(message = "Username Cannot Be Left Blank") String username,
			@Size(min = 2, message = "Length cannot Be Less Than 2") String firstname, String lastname, String email,
			String role, String ssn, List<Order> orders, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}



	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", orders=" + orders
				+ ", address=" + address + "]";
	}


}
