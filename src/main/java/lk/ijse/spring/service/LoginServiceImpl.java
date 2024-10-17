package lk.ijse.spring.service;

import lk.ijse.spring.dao.LoginDAO;
import lk.ijse.spring.dto.impl.UserDTO;
import lk.ijse.spring.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDAO loginDAO;
    @Override
    public boolean checkCredentials(UserDTO userDTO) {
        if (loginDAO.existsById(userDTO.getName())) {
            var selectedUser = loginDAO.getReferenceById(userDTO.getName());
            if (selectedUser.getPassword().equals(userDTO.getPassword())) {
                return true;
            }else {
                return false;
            }
        }else {
            throw new UserNotFoundException("User Not Found");
        }
    }
}
