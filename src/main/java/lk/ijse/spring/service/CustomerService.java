package lk.ijse.spring.service;

import lk.ijse.spring.dto.impl.CustomerDTO;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(String customerId, CustomerDTO customerDTO);
    void deleteCustomer(String customerId);
}
