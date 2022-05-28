package com.exam.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{
	
   
	private static final long serialVersionUID = 1L;
	
	
	@Id
      @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  private String firstName;
	  private String lastName;
	  private String email;
	  private String phone;
	  private String username;
	  private String password;
	  private String about;
	  private String image;
	  private boolean enable = true;
	  
	  //user has many roles   one to many
	  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="user")
	  @JsonIgnore
	  private Set<UserRole> userRoles = new HashSet<>();
	  

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", phone=" + phone + ", about=" + about + ", enable="
				+ enable + "]";
	}

	
	
	
	
	//All UserDetails interface methods required for Spring Boot security
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> set = new HashSet<>();
		 this.userRoles.forEach(userRole -> {
		       set.add(new Authority(userRole.getRole().getRoleName()));	 
		 });
		 return set;
	}

	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return this.enable;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  
}
