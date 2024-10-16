package lk.ijse.spring.service;

import lk.ijse.spring.dto.impl.PlaceOrderDTO;

public interface OrderService {
    void saveOrder(PlaceOrderDTO placeOrderDTO);
    String getLastOrderId();
}
