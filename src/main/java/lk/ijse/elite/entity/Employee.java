package lk.ijse.elite.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Employee {
    private String empid;
    private String adid;
    private String name;
    private String address;
    private String mobile;
    private String position;
    private String basicSalary;
}