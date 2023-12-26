package lk.ijse.elite.model.dto.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TodayAppointmentsTM {
    private String shedule_id;
    private String name;
    private String time;
    private String mobile;
}
