package com.copapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.copapp.model.Role;
import com.copapp.model.UserRole;

public interface RoleRepository extends JpaRepository<Role,Long>{

}
