package com.zviproject.dbchangeservice.component.repository;

import com.zviproject.dbchangeservice.component.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
