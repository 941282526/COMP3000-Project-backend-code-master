package com.example.restaurantAssistant.repository;

import com.example.restaurantAssistant.pojo.SeatConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yg
 */
@Repository
public interface SeatConfigRepository extends JpaRepository<SeatConfig, Integer> {

    SeatConfig findBySeatType(String seatType);

}
