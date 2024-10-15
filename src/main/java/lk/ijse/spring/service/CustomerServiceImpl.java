package lk.ijse.spring.service;

import lk.ijse.spring.dao.CustomerDAO;
import lk.ijse.spring.dto.impl.CustomerDTO;
import lk.ijse.spring.entity.impl.CustomerEntity;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
