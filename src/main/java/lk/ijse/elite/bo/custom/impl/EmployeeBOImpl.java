package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.EmployeeBO;
import lk.ijse.elite.entity.Employee;
import lk.ijse.elite.model.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public List<EmployeeDTO> loadAllEmployee() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateEmployeeId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Employee searchEmployeePosition(String position) throws SQLException, ClassNotFoundException {
        return null;
    }
}
