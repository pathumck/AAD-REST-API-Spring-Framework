package lk.ijse.spring.service;

import lk.ijse.spring.dao.CustomerDAO;
import lk.ijse.spring.dto.impl.CustomerDTO;
import lk.ijse.spring.entity.impl.CustomerEntity;
import lk.ijse.spring.exception.CustomerNotFoundException;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity =mapping.toCustomerEntity(customerDTO);
        CustomerEntity save =customerDAO.save(customerEntity);
        if (save == null) {
            throw new DataPersistException("Customer Not Saved");
        }
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        Optional<CustomerEntity> findCustomer =customerDAO.findById(customerId);
        if (!findCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer Not Found");
        }else {
            findCustomer.get().setId(customerDTO.getId());
            findCustomer.get().setName(customerDTO.getName());
            findCustomer.get().setAddress(customerDTO.getAddress());
            findCustomer.get().setPhone(customerDTO.getPhone());
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> findCustomer =customerDAO.findById(customerId);
        if (!findCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id " + customerId + " not found");
        }else {
            customerDAO.deleteById(customerId);
        }
    }
}
