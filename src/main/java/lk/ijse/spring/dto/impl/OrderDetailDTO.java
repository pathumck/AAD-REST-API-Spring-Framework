package lk.ijse.spring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailDTO {
    private String orderId;
    private String itemId;
    private Integer qty;
    private Double unitPrice;
    private Double totalPrice;
}
