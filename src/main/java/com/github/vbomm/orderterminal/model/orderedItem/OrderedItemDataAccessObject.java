package com.github.vbomm.orderterminal.model.orderedItem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedItemDataAccessObject {

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public void save(OrderedItem orderItem) {
        orderedItemRepository.save(orderItem);
    }

    public void delete(OrderedItem orderItem) {
        orderedItemRepository.delete(orderItem);
    }

    public List<OrderedItem> getAllOrderItems(){
        List<OrderedItem> orderItems = new ArrayList<>();
        Streamable.of(orderedItemRepository.findAll()).forEach(orderItems::add);

        return orderItems;
    }

    public List<OrderedItem> findByOrderIdAndItemId(long orderId, long itemId) {
        return orderedItemRepository.findByOrderIdAndItemId(orderId, itemId);
    }

    public List<OrderedItem> findByOrderId(long orderId) {
        return orderedItemRepository.findByOrderId(orderId);
    }
}
