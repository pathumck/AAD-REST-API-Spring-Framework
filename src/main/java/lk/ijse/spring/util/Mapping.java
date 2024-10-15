package lk.ijse.spring.util;

import lk.ijse.spring.dto.CustomerStatus;
import lk.ijse.spring.dto.impl.CustomerDTO;
import lk.ijse.spring.dto.impl.ItemDTO;
import lk.ijse.spring.entity.impl.CustomerEntity;
import lk.ijse.spring.entity.impl.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public ItemEntity toItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public ItemDTO toItemDTO(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDTO.class);
    }
    public List<ItemDTO> toItemDTOList(List<ItemEntity> itemEntities){
        return modelMapper.map(itemEntities, new TypeToken<List<ItemDTO>>(){}.getType());
    }
    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public List<CustomerDTO> toCustomerDTOList(List<CustomerEntity> customerEntities) {
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDTO>>(){}.getType());
    }
}
