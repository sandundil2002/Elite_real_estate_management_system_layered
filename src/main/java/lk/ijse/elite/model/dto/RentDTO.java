package lk.ijse.elite.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RentDTO {
    private String rentId;
    private String propertyId;
    private String customerId;
    private String date;
    private String duration;
}
