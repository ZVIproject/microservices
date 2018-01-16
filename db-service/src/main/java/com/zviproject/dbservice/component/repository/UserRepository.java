package com.zviproject.dbservice.component.repository;

import com.zviproject.dbservice.component.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
