package lk.ijse.elite.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Renting {
    private String rent_id;
    private String property_id;
    private String agent_id;
    private String date;
    private String duration;
}
