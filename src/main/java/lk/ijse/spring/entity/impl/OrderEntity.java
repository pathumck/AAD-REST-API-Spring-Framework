package lk.ijse.spring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.spring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Orders")
public class OrderEntity implements SuperEntity {
    @Id
    private String orderId;
    private Double total;
    private String date;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerEntity customerEntity;
}
