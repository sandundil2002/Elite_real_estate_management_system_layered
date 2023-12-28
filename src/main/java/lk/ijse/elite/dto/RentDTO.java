package lk.ijse.elite.dto;

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
