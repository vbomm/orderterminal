package com.github.vbomm.orderterminal.model.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemDataAccessObject {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }

    public Optional<Item> findById(int id) {
        return itemRepository.findById(id);
    }

    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<>();
        Streamable.of(itemRepository.findAll()).forEach(items::add);

        return items;
    }
}
