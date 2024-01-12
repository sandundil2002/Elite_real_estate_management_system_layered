package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.EmployeeDAO;
import lk.ijse.elite.entity.Employee;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> loadAll() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employee");
        List<Employee> employeeList = new ArrayList<>();

        while (resultSet.next()) {
            Employee employeeDto = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
            employeeList.add(employeeDto);
        }
        return employeeList;
    }

    @Override
    public boolean save(Employee dto) throws SQLException{
        return SQLUtil.sql("INSERT INTO employee VALUES (?,?,?,?,?,?,?)",
                dto.getEmpid(),
                dto.getAdid(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getPosition(),
                dto.getBasicSalary());
    }

    @Override
    public boolean update(Employee dto) throws SQLException{
        return SQLUtil.sql("UPDATE employee SET Admin_id=?,Name=?,Address=?,Mobile=?,Position=?,Basic_salary=? WHERE Employee_id=?",
                dto.getAdid(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getPosition(),
                dto.getBasicSalary(),
                dto.getEmpid());
    }

    @Override
    public Employee search(String id) throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employee WHERE Employee_id=?", id);

        if (resultSet.next()) {
            return new Employee(
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

    @Override
    public boolean delete(String id) throws SQLException{
        return SQLUtil.sql("DELETE FROM employee WHERE Employee_id=?", id);
    }

    @Override
    public String generateId() throws SQLException{
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

    @Override
    public Employee searchEmployeePosition(String position) throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM employee WHERE Position=?", position);

        if (resultSet.next()) {
            return new Employee(
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
}
