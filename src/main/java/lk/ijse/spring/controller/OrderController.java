package lk.ijse.spring.controller;

import lk.ijse.spring.dto.impl.PlaceOrderDTO;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        try {
            logger.info("Saving order: {}", placeOrderDTO);
            orderService.saveOrder(placeOrderDTO);
            logger.info("Order saved Successfully: {}", placeOrderDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException exception) {
            logger.error("Error while saving order: {}", placeOrderDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            logger.error("Unexpected Error while saving order: {}", placeOrderDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/nextid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getNextOrderId() {
        logger.info("Retrieving next order id");
        String lastId = orderService.getLastOrderId();
        if (lastId == null || lastId.isEmpty() || !lastId.matches("^O\\d+$")) {
            logger.info("No existing order id found. Returning default order id: O001");
            return new ResponseEntity<>("O001",HttpStatus.OK);
        } else {
            String numericPart = lastId.substring(3);
            int numericValue = Integer.parseInt(numericPart);
            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);
            logger.info("Next orderId generated: {}", "O00" + nextNumericPart);
            return new ResponseEntity<>("O00" + nextNumericPart, HttpStatus.OK);
        }
    }
}
