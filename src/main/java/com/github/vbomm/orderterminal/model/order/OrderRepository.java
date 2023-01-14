package com.github.vbomm.orderterminal.model.order;

import com.github.vbomm.orderterminal.model.orderedItem.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findBySessionIdAndStatus(String sessionId, int status);
}