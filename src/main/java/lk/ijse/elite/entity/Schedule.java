package lk.ijse.elite.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Schedule {
    private String ScheduleId;
    private String AdminId;
    private String Date;
    private String Time;
    private String Status;
}
