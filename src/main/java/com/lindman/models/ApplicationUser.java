package com.lindman.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class ApplicationUser implements UserDetails{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
	@Column(unique=true)
    private String username;
    private String password;
	private String full_name;
	private String email;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Set<Role> authorities;

	@OneToMany
	private Set<Listing> listings;

    public ApplicationUser() {
		super();
		authorities = new HashSet<>();
	}
	

	public ApplicationUser(Integer userId, String username, String password,String full_name, String email, Set<Role> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.full_name= full_name;

		this.authorities = authorities;
	}

    public Integer getUserId() {
		return this.userId;
	}
	
	public void setId(Integer userId) {
		this.userId = userId;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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
		// TODO Auto-generated method stub
		return true;
	}
    
}
