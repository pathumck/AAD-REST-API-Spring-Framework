package lk.ijse.spring.dto.impl;

import lk.ijse.spring.dto.CustomerStatus;
import lk.ijse.spring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements CustomerStatus {
    private String id;
    private String name;
    private String address;
    private String phone;
}
