package com.github.vbomm.orderterminal.model.orderedItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {
    List<OrderedItem> findByOrderIdAndItemId(long orderId, long itemId);

    List<OrderedItem> findByOrderId(long orderId);
}
