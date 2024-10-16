package lk.ijse.spring.dao;

import lk.ijse.spring.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<OrderEntity, String> {
    @Query(value = "SELECT orderId FROM Orders WHERE orderId LIKE 'O00%' ORDER BY CAST(SUBSTRING(orderId, 4) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String getLastOrderId();
}
