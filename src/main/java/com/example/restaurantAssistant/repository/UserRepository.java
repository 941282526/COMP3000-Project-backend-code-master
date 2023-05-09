package com.example.restaurantAssistant.repository;

import com.example.restaurantAssistant.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findByOpenId(String openId);
}
