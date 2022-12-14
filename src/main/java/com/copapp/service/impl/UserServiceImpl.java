package com.copapp.service.impl;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.copapp.exception.ResourceFoundException;
import com.copapp.model.User;
import com.copapp.model.UserRole;
import com.copapp.repo.RoleRepository;
import com.copapp.repo.UserRepository;
import com.copapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
       
        User local=this.userRepository.findUserByEmail(user.getUsername());

        if(local!=null) throw new ResourceFoundException("User Already Present !!"); 
        else{
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            
            this.userRepository.save(user);

        }
        return user;
    }


    @Override
    public User getUser(String email) {
        
         return this.userRepository.findUserByEmail(email);
    }
	
	
    @Override
	public void updatePassword(String email, String newPassword) {
    	User user=this.userRepository.findUserByEmail(email);
    	user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
		this.userRepository.save(user);
	}

}
