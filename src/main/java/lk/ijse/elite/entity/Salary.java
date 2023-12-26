package lk.ijse.elite.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Salary {
    private String salary_id;
    private String employee_id;
    private String date;
    private String amount;
}
