package com.hs.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_data")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "username is mandatory field.")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String username;
	
	@Size(min = 2, message = "First Name should have minimum 2 charecter")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	private String firstname;
	
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	private String  lastname;
	
	@Column(name = "EMAIl", length = 50, nullable = false)
	private String email;
	
	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;
	
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	private String ssn;

	@OneToMany(mappedBy = "user")
	//@JoinColumn(name = "orderId")
	private List<Order> orders;
	
	public User() {
		}
	
	
	

	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
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




	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	
	

}
