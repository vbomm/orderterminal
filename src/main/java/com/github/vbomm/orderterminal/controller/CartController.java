package com.github.vbomm.orderterminal.controller;

import com.github.vbomm.orderterminal.model.item.Item;
import com.github.vbomm.orderterminal.model.item.ItemDataAccessObject;
import com.github.vbomm.orderterminal.model.order.Order;
import com.github.vbomm.orderterminal.model.order.OrderDataAccessObject;
import com.github.vbomm.orderterminal.model.orderedItem.OrderedItem;
import com.github.vbomm.orderterminal.model.orderedItem.OrderedItemDataAccessObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private ItemDataAccessObject itemDataAccessObject;

    @Autowired
    private OrderedItemDataAccessObject orderedItemDataAccessObject;

    @Autowired
    private OrderDataAccessObject orderDataAccessObject;

    @GetMapping("/api/add/{id}")
    public String addItem(@PathVariable int id, HttpSession httpSession) {
        System.out.println(httpSession.getId() + " adds " + id);
        List<Order> orders = orderDataAccessObject.findBySessionAndStatus(httpSession.getId(), 0);
        Order order = orders.get(0);

        Optional<Item> item = itemDataAccessObject.findById(id);
        if (item.isPresent()) {
            System.out.println("Looking for " + id + " in " + order.getId());
            List<OrderedItem> orderedItems = orderedItemDataAccessObject.findByOrderIdAndItemId(order.getId(), id);
            System.out.println(orderedItems);
            if (orderedItems.size() == 0) {
                System.out.println("Item with ID " + id + " added to order");
                orderedItemDataAccessObject.save(new OrderedItem(item.get(), order, 1, item.get().getPrice()));
            } else {
                System.out.println("Item with ID " + id + " already exists, increasing quantity");
                orderedItems.get(0).setQuantity(orderedItems.get(0).getQuantity() + 1);
                orderedItemDataAccessObject.save(orderedItems.get(0));
            }
        } else
            System.err.println("Item with ID " + id + " not found");

        return "redirect:/";
    }

    @GetMapping("/api/payAndOrder")
    public String payAndOrder(HttpSession httpSession) {
        List<Order> orders = orderDataAccessObject.findBySessionAndStatus(httpSession.getId(), 0);
        Order order = orders.get(0);

        List<OrderedItem> items = orderedItemDataAccessObject.findByOrderId(order.getId());
        for (var item : items)
            orderedItemDataAccessObject.delete(item);

        System.out.println(httpSession.getId() + " payAndOrder");

        return "redirect:/";
    }

    @GetMapping("/api/cancel")
    public String cancel(HttpSession httpSession) {
        List<Order> orders = orderDataAccessObject.findBySessionAndStatus(httpSession.getId(), 0);
        Order order = orders.get(0);

        List<OrderedItem> items = orderedItemDataAccessObject.findByOrderId(order.getId());
        for (var item : items)
            orderedItemDataAccessObject.delete(item);

        System.out.println(httpSession.getId() + " cancel");

        return "redirect:/";
    }
}
