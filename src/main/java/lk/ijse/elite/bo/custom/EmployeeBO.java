package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.entity.Employee;
import lk.ijse.elite.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    List<EmployeeDTO> loadAllEmployee() throws SQLException,ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO dto) throws SQLException,ClassNotFoundException;

    EmployeeDTO searchEmployee(String id) throws SQLException,ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException,ClassNotFoundException;

    String generateEmployeeId() throws SQLException,ClassNotFoundException;

    Employee searchEmployeePosition(String position) throws SQLException, ClassNotFoundException;
}
