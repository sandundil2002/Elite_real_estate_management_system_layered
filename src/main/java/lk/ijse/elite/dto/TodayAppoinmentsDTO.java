package lk.ijse.elite.dto;

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
