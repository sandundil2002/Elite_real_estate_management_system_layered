package lk.ijse.elite.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Rent {
    private String rentId;
    private String propertyId;
    private String customerId;
    private String date;
    private String duration;
}
