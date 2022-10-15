package com.copapp.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User implements UserDetails{

	@Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private String username;
      private String password;
      @Column(unique=true)	
      private String email;
      private String mobileNo;
      private boolean enabled=true;
      private String otp;      
      @CreationTimestamp
      private LocalDateTime createdAt;
      @UpdateTimestamp
      private LocalDateTime updatedAt; 
    
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
      @JsonIgnore
      private Set<UserRole> userRoles=new HashSet<>(); 
     
     
      public User() {
      
      }

      public User(Long id, String username, String password, String email, boolean enabled) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.email = email;
            this.enabled = enabled;
      }

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
          Set<SimpleGrantedAuthority> set=new HashSet<>();
          this.userRoles.forEach(userRole -> {
                set.add(new SimpleGrantedAuthority(userRole.getRole().getRoleName()));
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
    
      public Long getId() {
            return id;
      }
        
      public void setId(Long id) {
            this.id = id;
      }

      public String getUsername() {
            return email;
      }

      public void setUsername(String username) {
            this.username = username;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public boolean isEnabled() {
            return enabled;
      }

      public void setEnabled(boolean enabled) {
            this.enabled = enabled;
      }
      
      public Set<UserRole> getUserRoles() {
            return userRoles;
      }

      public void setUserRoles(Set<UserRole> userRoles) {
            this.userRoles = userRoles;
      }

      public String getMobileNo() {
            return mobileNo;
      }

      public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
      }
      public String getUser_name(){
            return username;
      }

	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
     
      
    
}