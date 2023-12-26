package lk.ijse.elite.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AdminDTO {
    private String Admin_id;
    private String Name;
    private String Address;
    private String Mobile;
    private String Email;
    private String Password;
}
