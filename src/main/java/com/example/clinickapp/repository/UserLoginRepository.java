package com.example.clinickapp.repository;

import com.example.clinickapp.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
}
