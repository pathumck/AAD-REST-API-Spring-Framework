package lk.ijse.spring.service;

import lk.ijse.spring.dto.ItemStatus;
import lk.ijse.spring.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String itemId, ItemDTO itemDTO);
    void deleteItem(String itemId);
    ItemStatus getSelectedItem(String itemId);
    List<ItemDTO> getAllItems();
}
