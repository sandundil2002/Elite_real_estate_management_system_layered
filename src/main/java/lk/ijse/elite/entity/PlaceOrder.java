package lk.ijse.elite.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrder {
    private String paymentId;
    private String customerId;
    private String propertyId;
    private String name;
    private String date;
    private String price;
    private String address;
    private String method;
}
