package com.github.vbomm.orderterminal.controller;

import com.github.vbomm.orderterminal.datatransferobject.CartItem;
import com.github.vbomm.orderterminal.model.item.Item;
import com.github.vbomm.orderterminal.model.item.ItemDataAccessObject;
import com.github.vbomm.orderterminal.model.order.Order;
import com.github.vbomm.orderterminal.model.order.OrderDataAccessObject;
import com.github.vbomm.orderterminal.model.orderedItem.OrderedItemDataAccessObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TerminalController {

    @Autowired
    private ItemDataAccessObject itemDataAccessObject;

    @Autowired
    private OrderedItemDataAccessObject orderedItemDataAccessObject;

    @Autowired
    private OrderDataAccessObject orderDataAccessObject;

    @GetMapping("/")
    public String home(Model model, HttpSession httpSession) {
        System.out.println(httpSession.getId() + " requested home");

        model.addAttribute("items", generateItemList());
        model.addAttribute("cart", generateCart(httpSession, model));

        return "terminal";
    }

    private List<Item> generateItemList() {
        return itemDataAccessObject.getAllItems();
    }

    private List<CartItem> generateCart(HttpSession httpSession, Model model) {
        List<Order> orders = orderDataAccessObject.findBySessionAndStatus(httpSession.getId(), 0);
        Order order;

        if (orders.size() == 0) {
            // new connection, create new order
            order = new Order(httpSession.getId(), (byte) 0);
            orderDataAccessObject.save(order);
        } else
            order = orders.get(0);

        BigDecimal fullPrice = BigDecimal.valueOf(0);
        List<CartItem> cart = new ArrayList<>();
        for (var cartItem : orderedItemDataAccessObject.findByOrderId(order.getId())) {
            cart.add(new CartItem(cartItem.getItem().getTitle(), cartItem.getUnitPrice(), cartItem.getQuantity()));
            fullPrice = fullPrice.add(cartItem.getUnitPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }

        model.addAttribute("fullPrice", fullPrice);

        return cart;
    }
}
