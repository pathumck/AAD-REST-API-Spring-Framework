package lk.ijse.spring.controller;

import lk.ijse.spring.customStatusCodes.SelectedItemAndCustomerErrorStatus;
import lk.ijse.spring.dto.ItemStatus;
import lk.ijse.spring.dto.impl.ItemDTO;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.exception.ItemNotFoundException;
import lk.ijse.spring.service.ItemService;
import lk.ijse.spring.util.RegexProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDTO) {
        try {
            logger.info("Saving item: {}", itemDTO);
            itemService.saveItem(itemDTO);
            logger.info("Item saved: {}", itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException exception) {
            logger.error("Error while saving item: {}", itemDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            logger.error("Unexpected error while saving item: {}", itemDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable ("itemId") String itemId, @RequestBody ItemDTO itemDTO) {
        try {
            if (!RegexProcess.itemIdValidation(itemId) || itemDTO == null) {
                logger.warn("Invalid itemId or itemDTO: {}", itemId, itemDTO);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("Updating item with id: {}", itemId);
            itemService.updateItem(itemId, itemDTO);
            logger.info("Item updated successfully: {}", itemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException exception) {
            logger.error("Item not found: {}", itemId, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            logger.error("Unexpected error while updating item: {}", itemDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemId") String itemId) {
        try {
            if (!RegexProcess.itemIdValidation(itemId)) {
                logger.warn("Invalid itemId: {}", itemId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("Deleting item with id: {}", itemId);
            itemService.deleteItem(itemId);
            logger.info("Item deleted successfully: {}", itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException exception) {
            logger.error("Item not found: {}", itemId, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception exception) {
            logger.error("Unexpected error while deleting item: {}", itemId, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getSelectedItem(@PathVariable("itemId") String itemId) {
        if (!RegexProcess.itemIdValidation(itemId)) {
            logger.warn("Invalid item id: {}", itemId);
            return new SelectedItemAndCustomerErrorStatus(1, "Invalid Item Id");
        }
        logger.info("Retrieving selected item with id: {}", itemId);
        return itemService.getSelectedItem(itemId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItems() {
        logger.info("Retrieving all items");
        return itemService.getAllItems();
    }

}
