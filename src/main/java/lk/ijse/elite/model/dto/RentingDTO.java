package lk.ijse.elite.model.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RentingDTO {
    private String rent_id;
    private String property_id;
    private String agent_id;
    private String date;
    private String duration;
}
