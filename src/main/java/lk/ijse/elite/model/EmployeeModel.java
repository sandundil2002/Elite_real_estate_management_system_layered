package lk.ijse.elite.model;

import lk.ijse.elite.model.dto.EmployeeDTO;
import lk.ijse.elite.util.SQLUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static List<EmployeeDTO> loadAllEmployees() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employee");
        List<EmployeeDTO> employeeList = new ArrayList<>();

        while (resultSet.next()) {
            employeeList.add(new EmployeeDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return employeeList;
    }

    public static int getEmployeeCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT COUNT(*) FROM employee");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO employee VALUES (?,?,?,?,?,?,?)",
                dto.getEmpid(),dto.getAdid(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getPosition(),dto.getBasicSalary());
    }

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("UPDATE employee SET Admin_id=?,Name=?,Address=?,Mobile=?,Position=?,Basic_salary=? WHERE Employee_id=?",
                dto.getAdid(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getPosition(),dto.getBasicSalary(),dto.getEmpid());
    }

    public EmployeeDTO searchEmployee(String eid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employee WHERE Employee_id=?", eid);

        if (resultSet.next()) {
            return new EmployeeDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;
    }

    public static EmployeeDTO searchEmployeePosition(String position) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employee WHERE Position=?", position);

        if (resultSet.next()) {
            return new EmployeeDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;
    }

    public boolean deleteEmployee(String eid) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("DELETE FROM employee WHERE Employee_id=?", eid);
    }

    public String generateEmployeeId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT Employee_id FROM employee ORDER BY Employee_id DESC LIMIT 1");
        if(resultSet.next()){
            String id = resultSet.getString("Employee_id");
            String numericPart = id.replaceAll("\\D", "");
            int newEmployeeId = Integer.parseInt(numericPart) + 1 ;
            return String.format("E%03d", newEmployeeId);
        } else {
            return "E001";
        }
    }
}
