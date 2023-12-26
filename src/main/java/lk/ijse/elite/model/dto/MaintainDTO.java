package lk.ijse.elite.model.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MaintainDTO {
    private String maintain_id;
    private String rent_id;
    private String date;
    private String status;
}
