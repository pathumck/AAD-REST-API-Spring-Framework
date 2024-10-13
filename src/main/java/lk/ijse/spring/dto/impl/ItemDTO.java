package lk.ijse.spring.dto.impl;

import lk.ijse.spring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements SuperDTO {
    private String id;
    private String name;
    private Double price;
    private Integer qty;
}
