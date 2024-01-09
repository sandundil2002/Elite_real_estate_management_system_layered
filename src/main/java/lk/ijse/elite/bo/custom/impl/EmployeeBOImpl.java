package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.EmployeeBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.EmployeeDAO;
import lk.ijse.elite.entity.Employee;
import lk.ijse.elite.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public List<EmployeeDTO> loadAllEmployee() throws SQLException, ClassNotFoundException {
        List<Employee> employees = employeeDAO.loadAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees){
            employeeDTOS.add(new EmployeeDTO(
                    employee.getEmpid(),
                    employee.getAdid(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getMobile(),
                    employee.getPosition(),
                    employee.getBasicSalary()));
        }
        return employeeDTOS;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                dto.getEmpid(),
                dto.getAdid(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getPosition(),
                dto.getBasicSalary()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                dto.getEmpid(),
                dto.getAdid(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getPosition(),
                dto.getBasicSalary()));
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        if (employee != null) {
            return new EmployeeDTO(
                    employee.getEmpid(),
                    employee.getAdid(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getMobile(),
                    employee.getPosition(),
                    employee.getBasicSalary());
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateId();
    }

    @Override
    public Employee searchEmployeePosition(String position) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchEmployeePosition(position);
    }
}
