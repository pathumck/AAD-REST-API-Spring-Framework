package lk.ijse.spring.service;

import lk.ijse.spring.dao.OrderDAO;
import lk.ijse.spring.dto.impl.CartDTO;
import lk.ijse.spring.dto.impl.OrderDetailDTO;
import lk.ijse.spring.dto.impl.PlaceOrderDTO;
import lk.ijse.spring.entity.impl.CustomerEntity;
import lk.ijse.spring.entity.impl.OrderEntity;
import lk.ijse.spring.exception.DataPersistException;
import lk.ijse.spring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ItemService itemService;
    @Override
    public void saveOrder(PlaceOrderDTO placeOrderDTO) {
        //save order
        OrderEntity orderEntity = mapping.toOrderEntity(placeOrderDTO);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(placeOrderDTO.getCustomerId());
        orderEntity.setCustomerEntity(customerEntity);
        OrderEntity save = orderDAO.save(orderEntity);
        if (save == null) {
            throw new DataPersistException("Order Not Saved");
        }

        List<CartDTO> cartDTOList = placeOrderDTO.getCartDTOList();
        for (CartDTO cartDTO : cartDTOList) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                    placeOrderDTO.getOrderId(),
                    cartDTO.getCode(),
                    cartDTO.getQty(),
                    cartDTO.getPrice(),
                    cartDTO.getTotal()
            );

            //update item qty
            itemService.updateItemQty(cartDTO.getCode(), cartDTO.getQty());
            //save order detail
            orderDetailService.saveOrderDetail(orderDetailDTO);
        }
    }
}
