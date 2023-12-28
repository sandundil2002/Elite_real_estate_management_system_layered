package lk.ijse.elite.dao.custom;

import lk.ijse.elite.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee> {
    Employee searchEmployeePosition(String position) throws SQLException, ClassNotFoundException;
}
