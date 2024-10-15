package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerStatus;
import lk.ijse.spring.dto.impl.CustomerDTO;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.saveCustomer(customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDTO customerDTO) {
        try {
            customerService.updateCustomer(customerId, customerDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedCustomer(@PathVariable("customerId") String customerId) {
        return customerService.getSelectedCustomer(customerId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
