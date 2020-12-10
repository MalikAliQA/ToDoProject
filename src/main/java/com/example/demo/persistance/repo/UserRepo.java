package com.example.demo.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.persistance.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
