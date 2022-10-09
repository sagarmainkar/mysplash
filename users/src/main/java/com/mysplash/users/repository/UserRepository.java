package com.mysplash.users.repository;


import com.mysplash.users.dto.SplashUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<SplashUser,Integer> {
}
