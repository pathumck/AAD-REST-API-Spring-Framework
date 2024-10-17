package lk.ijse.spring.controller;

import lk.ijse.spring.customStatusCodes.SelectedItemAndCustomerErrorStatus;
import lk.ijse.spring.dto.CustomerStatus;
import lk.ijse.spring.dto.impl.CustomerDTO;
import lk.ijse.spring.exception.CustomerNotFoundException;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            logger.info("Saving customer: {}", customerDTO);
            customerService.saveCustomer(customerDTO);
            logger.info("Customer saved: {}", customerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException exception) {
            logger.error("Error while saving customer: {}", customerDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            logger.error("Unexpected error while saving customer: {}", customerDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDTO customerDTO) {
        try {
            if (!RegexProcess.customerIdValidation(customerId) || customerDTO == null) {
                logger.warn("Invalid customer id or customerDTO: {}", customerId, customerDTO);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("Updating customer with id: {}", customerId);
            customerService.updateCustomer(customerId, customerDTO);
            logger.info("Customer updated successfully: {}", customerDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException exception) {
            logger.error("Customer not found: {}", customerId, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception exception) {
            logger.error("Unexpected error while updating customer: {}", customerDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        try {
            if (!RegexProcess.customerIdValidation(customerId)) {
                logger.warn("Invalid customer id: {}", customerId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("Deleting customer with id: {}", customerId);
            customerService.deleteCustomer(customerId);
            logger.info("Customer deleted successfully: {}", customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException exception) {
            logger.error("Customer not found: {}", customerId, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception exception) {
            logger.error("Unexpected error while deleting customer: {}", customerId, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedCustomer(@PathVariable("customerId") String customerId) {
        if (!RegexProcess.customerIdValidation(customerId)) {
            logger.warn("Invalid customer id: {}", customerId);
            return new SelectedItemAndCustomerErrorStatus(1, "Invalid Customer Id");
        }
        logger.info("Retrieving selected customer with id: {}", customerId);
        return customerService.getSelectedCustomer(customerId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        logger.info("Retrieving all customers");
        return customerService.getAllCustomers();
    }
}
