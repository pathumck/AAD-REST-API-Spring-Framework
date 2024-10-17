package lk.ijse.spring.controller;

import lk.ijse.spring.dto.impl.UserDTO;
import lk.ijse.spring.exception.UserNotFoundException;
import lk.ijse.spring.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> checkCredentials(@RequestBody UserDTO userDTO) {
        logger.info("Received login request: {}", userDTO);
        try {
            boolean isValid = loginService.checkCredentials(userDTO);
            if (isValid) {
                logger.info("Login successful: {}", userDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                logger.warn("Login failed: {}", userDTO);
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch (UserNotFoundException exception) {
            logger.error("User not found: {}", userDTO.getName(), exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception exception) {
            logger.error("Unexpected error while checking credentials: {}", userDTO, exception);
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
