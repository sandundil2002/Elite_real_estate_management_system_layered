package lk.ijse.elite.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Admin {
    private String Admin_id;
    private String Name;
    private String Otp;
    private String Mobile;
    private String Email;
    private String Password;
}
