package lk.ijse.spring.customStatusCodes;

import lk.ijse.spring.dto.CustomerStatus;
import lk.ijse.spring.dto.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedItemAndCustomerErrorStatus implements ItemStatus, CustomerStatus {
    private int statusCode;
    private String statusMessage;
}
