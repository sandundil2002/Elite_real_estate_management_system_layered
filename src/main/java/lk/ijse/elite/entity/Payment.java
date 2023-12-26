package lk.ijse.elite.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Payment {
    private String payment_id;
    private String customer_id;
    private String property_id;
    private String date;
    private String price;
    private String method;
}
