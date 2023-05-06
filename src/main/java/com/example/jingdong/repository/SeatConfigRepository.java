package com.example.jingdong.repository;

import com.example.jingdong.pojo.SeatConfig;
import com.example.jingdong.pojo.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yg
 */
@Repository
public interface SeatConfigRepository extends JpaRepository<SeatConfig, Integer> {

    SeatConfig findBySeatType(String seatType);

}
