package lk.ijse.elite.model.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TodayAppoinmentsDTO {
    private String shedule_id;
    private String name;
    private String time;
    private String mobile;
}
