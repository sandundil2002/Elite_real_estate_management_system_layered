package lk.ijse.elite.model.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SalaryDTO {
    private String salary_id;
    private String employee_id;
    private String date;
    private String amount;
}
