package lk.ijse.elite.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Property {
    private String propertyId;
    private String agentId;
    private String price;
    private String address;
    private String type;
    private String status;
    private String perches;
}
