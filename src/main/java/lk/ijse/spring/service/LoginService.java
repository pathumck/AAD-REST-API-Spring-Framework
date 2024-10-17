package lk.ijse.spring.service;

import lk.ijse.spring.dto.impl.UserDTO;

public interface LoginService {
    boolean checkCredentials(UserDTO userDTO);
}
