package com.github.vbomm.orderterminal.model.order;

import com.github.vbomm.orderterminal.model.orderedItem.OrderedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDataAccessObject {

    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public List<Order> findBySessionAndStatus(String session, int status) {
        return orderRepository.findBySessionIdAndStatus(session, status);
    }

    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        Streamable.of(orderRepository.findAll()).forEach(orders::add);

        return orders;
    }
}
