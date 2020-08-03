package com.segmentfault.springbootlesson11.repository;

import com.segmentfault.springbootlesson11.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 62667
 */

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByState(String state);


}
