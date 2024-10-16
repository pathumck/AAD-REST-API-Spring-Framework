package lk.ijse.spring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDTO {
    private String code;
    private String name;
    private Double price;
    private Integer qty;
    private Double total;
}
