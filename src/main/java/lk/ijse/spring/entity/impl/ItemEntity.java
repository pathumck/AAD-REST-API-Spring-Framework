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
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private Double price;
    private Integer qty;

    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;
}
