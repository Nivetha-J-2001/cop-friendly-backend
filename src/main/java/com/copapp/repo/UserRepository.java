package com.copapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.copapp.model.Role;
import com.copapp.model.User;
import com.copapp.model.UserRole;

public interface UserRepository extends JpaRepository<User,Long>{
       
	public User findUserByEmail(String email);
    
}
