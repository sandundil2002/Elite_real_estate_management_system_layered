package lk.ijse.elite.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ScheduleDTO {
    private String ScheduleId;
    private String AdminId;
    private String Date;
    private String Time;
    private String Status;
}
