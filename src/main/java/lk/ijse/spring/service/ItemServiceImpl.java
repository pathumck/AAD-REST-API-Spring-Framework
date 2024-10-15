package lk.ijse.spring.service;

import lk.ijse.spring.dao.ItemDAO;
import lk.ijse.spring.dto.ItemStatus;
import lk.ijse.spring.dto.impl.ItemDTO;
import lk.ijse.spring.entity.impl.ItemEntity;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.exception.ItemNotFoundException;
import lk.ijse.spring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDAO itemDAO;
    @Autowired
    Mapping mapping;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = mapping.toItemEntity(itemDTO);
        ItemEntity save = itemDAO.save(itemEntity);
        if (save == null) {
            throw new DataPersistException("Item Not Saved");
        }
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        Optional<ItemEntity> findNote = itemDAO.findById(itemId);
        if (!findNote.isPresent()) {
            throw new ItemNotFoundException("Item Not Found");
        }else {
            findNote.get().setId(itemDTO.getId());
            findNote.get().setName(itemDTO.getName());
            findNote.get().setPrice(itemDTO.getPrice());
            findNote.get().setQty(itemDTO.getQty());
        }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> findNote = itemDAO.findById(itemId);
        if (!findNote.isPresent()) {
            throw new ItemNotFoundException("Item with id " + itemId + " not found");
        }else {
            itemDAO.deleteById(itemId);
        }
    }

    @Override
    public ItemStatus getSelectedItem(String itemId) {
        if (itemDAO.existsById(itemId)) {
            var selectedItem = itemDAO.getReferenceById(itemId);
            return mapping.toItemDTO(selectedItem);
        }else {
            throw new ItemNotFoundException();
        }
    }

}
