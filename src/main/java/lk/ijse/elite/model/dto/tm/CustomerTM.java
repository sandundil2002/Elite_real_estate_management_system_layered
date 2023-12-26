package lk.ijse.elite.model.dto.tm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerTM {
    private String customer_id;
    private String shedule_id;
    private String name;
    private String address;
    private String mobile;
    private String email;
}
