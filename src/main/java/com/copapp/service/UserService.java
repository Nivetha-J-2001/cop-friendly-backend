package com.copapp.service;

import java.util.Set;

import com.copapp.model.Role;
import com.copapp.model.User;
import com.copapp.model.UserRole;

public interface UserService {
    
    public User createUser(User user,Set<UserRole> userRoles) throws Exception;
    
    public User getUser(String email);
    
    public void updatePassword(String email, String newPassword);

//	public Long loginUser(String email, String password);
//   

}
