package lk.ijse.elite.model.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDTO {
    private String customer_id;
    private String shedule_id;
    private String name;
    private String address;
    private String mobile;
    private String email;
}
