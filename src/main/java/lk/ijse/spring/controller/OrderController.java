package lk.ijse.spring.controller;

import lk.ijse.spring.dto.impl.PlaceOrderDTO;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        try {
            orderService.saveOrder(placeOrderDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/nextid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getNextOrderId() {
        String lastId = orderService.getLastOrderId();
        if (lastId == null || lastId.isEmpty() || !lastId.matches("^O\\d+$")) {
            return new ResponseEntity<>("O001",HttpStatus.OK);
        } else {
            String numericPart = lastId.substring(3);
            int numericValue = Integer.parseInt(numericPart);

            int nextNumericValue = numericValue + 1;
            String nextNumericPart = String.format("%0" + numericPart.length() + "d", nextNumericValue);

            return new ResponseEntity<>("O00" + nextNumericPart, HttpStatus.OK);
        }
    }
}
