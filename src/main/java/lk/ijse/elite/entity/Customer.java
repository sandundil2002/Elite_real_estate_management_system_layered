package lk.ijse.elite.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Customer {
    private String customer_id;
    private String shedule_id;
    private String name;
    private String address;
    private String mobile;
    private String email;
}
