package lk.ijse.spring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.spring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orderdetail")
public class OrderDetailEntity implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private OrderEntity orderEntity;
    @ManyToOne
    @JoinColumn(name = "itemId", nullable = false)
    private ItemEntity itemEntity;

    private Integer qty;
    private Double unitPrice;
    private Double totalPrice;
}
