package lk.ijse.spring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;
}
