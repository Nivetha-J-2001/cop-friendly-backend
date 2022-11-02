package com.copapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.copapp.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
       
	public User findUserByEmail(String email);
    
}
