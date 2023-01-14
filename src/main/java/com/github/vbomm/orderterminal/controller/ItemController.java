package com.github.vbomm.orderterminal.controller;

import com.github.vbomm.orderterminal.model.item.Item;
import com.github.vbomm.orderterminal.model.item.ItemDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemDataAccessObject itemDataAccessObject;

    @GetMapping("/item/getAll")
    public List<Item> getAllItems() {

        return itemDataAccessObject.getAllItems();
    }
}
