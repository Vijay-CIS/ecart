package com.demo.ecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecart.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
