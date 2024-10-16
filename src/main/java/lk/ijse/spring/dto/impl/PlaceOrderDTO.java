package lk.ijse.spring.dto.impl;

import lk.ijse.spring.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO implements SuperDTO {
    private String orderId;
    private String customerId;
    private Double total;
    private String date;
    private List<CartDTO> cartDTOList;
}
