package lk.ijse.spring.dao;

import lk.ijse.spring.entity.impl.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetailEntity, String> {
}
