package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerStatus;
import lk.ijse.spring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String customerId, CustomerDTO customerDTO);
    void deleteCustomer(String customerId);
    CustomerStatus getSelectedCustomer(String customerId);
    List<CustomerDTO> getAllCustomers();
}
