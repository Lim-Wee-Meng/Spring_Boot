package org.generation.MyWebProject.service;

import org.generation.MyWebProject.repository.Entity.Item;
import java.util.List;

public interface ItemService {

    Item save(Item item);
    void delete (int itemId);
    List<Item> all();
    Item findById(int itemId);
}
