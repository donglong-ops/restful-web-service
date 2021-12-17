package com.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.restfulapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
