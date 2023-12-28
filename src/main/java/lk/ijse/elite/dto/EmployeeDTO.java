package lk.ijse.elite.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {
    private String empid;
    private String adid;
    private String name;
    private String address;
    private String mobile;
    private String position;
    private String basicSalary;
}
