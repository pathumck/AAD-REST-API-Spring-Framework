package lk.ijse.spring.service;

import lk.ijse.spring.dto.impl.ItemDTO;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String itemId, ItemDTO itemDTO);
}
