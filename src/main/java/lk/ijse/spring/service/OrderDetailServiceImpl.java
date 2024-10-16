package lk.ijse.spring.service;

import jakarta.transaction.Transactional;
import lk.ijse.spring.dao.OrderDetailDAO;
import lk.ijse.spring.dto.impl.OrderDetailDTO;
import lk.ijse.spring.entity.impl.ItemEntity;
import lk.ijse.spring.entity.impl.OrderDetailEntity;
import lk.ijse.spring.entity.impl.OrderEntity;
import lk.ijse.spring.exception.DataPersistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Override
    public void saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        //OrderEntity carrying orderId
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderDetailDTO.getOrderId());

        //ItemEntity carrying itemId
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(orderDetailDTO.getItemId());

        //OrderDetailEntity carrying order details
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity(
                null,
                orderEntity,
                itemEntity,
                orderDetailDTO.getQty(),
                orderDetailDTO.getUnitPrice(),
                orderDetailDTO.getTotalPrice()
        );

        //save
        OrderDetailEntity save = orderDetailDAO.save(orderDetailEntity);
        if (save == null) {
            throw new DataPersistException("Order Detail Not Saved");
        }
    }
}
