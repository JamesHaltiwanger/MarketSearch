package com.groupproj.marketsearch.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
//		Atributes >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		@Id
		@GeneratedValue (strategy=GenerationType.IDENTITY)
		private Long id;
		@Column(updatable=false)
		@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
		private Date createdAt;
		@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
		private Date updatedAt;
		@PrePersist
		protected void onCreate(){
			this.createdAt = new Date();
		}
		@PreUpdate
		protected void onUpdate(){
			this.updatedAt = new Date();
		}
		@NotBlank(message="Email Required")
		@Email(message="Invalid Email")
		@Pattern(regexp=".[A-Za-z0-9._%+-]+@[A-Za-z0-9.-].+\\..[a-z]{2,6}$", message="Invalid Email")
		private String email;
		@NotBlank(message="First Name Required")
		@Size(min=2, max=255, message="Must be 2-255 characters")
		private String firstName;
		@NotBlank(message="Last Name Required")
		@Size(min=2, max=255, message="Must be 2-255 characters")
		private String lastName;
		@NotBlank(message="Password Required")
		private String password;
		@NotBlank(message="Password Comparison Required")
		@Transient
		private String confirmPassword;
//		Table Realationships >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "wishlist", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "dbproduct_id")
	    )
	    private List<DBProduct> usersWishes;
		
//		Constructor Bean >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		public User() {
			
		}
//		Getter/Setter Pairs >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		public List<DBProduct> getUsersWishlist() {
			return this.usersWishes;
		}
		public void setUsersWishlist(List<DBProduct> products) {
			this.usersWishes = products;
		}
		public Long getId() {
			return this.id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Date getCreatedAt() {
			return this.createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return this.updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getEmail() {
			return this.email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFirstName() {
			return this.firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return this.lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPassword() {
			return this.password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getConfirmPassword() {
			return this.confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
}
