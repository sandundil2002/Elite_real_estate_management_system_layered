package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.SalaryBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.SalaryDAO;
import lk.ijse.elite.entity.Salary;
import lk.ijse.elite.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SALARY);
    @Override
    public List<SalaryDTO> loadAllSalary() throws SQLException, ClassNotFoundException {
        List<Salary> salaries = salaryDAO.loadAll();
        List<SalaryDTO> salaryDTOS = new ArrayList<>();

        for (Salary salary : salaries){
            salaryDTOS.add(new SalaryDTO(
                    salary.getSalary_id(),
                    salary.getEmployee_id(),
                    salary.getDate(),
                    salary.getAmount()));
        }
        return salaryDTOS;
    }

    @Override
    public boolean saveSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(
                dto.getSalary_id(),
                dto.getEmployee_id(),
                dto.getDate(),
                dto.getAmount()));
    }

    @Override
    public boolean updateSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(
                dto.getSalary_id(),
                dto.getEmployee_id(),
                dto.getDate(),
                dto.getAmount()));
    }

    @Override
    public SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException {
        Salary salary = salaryDAO.search(id);
        return new SalaryDTO(
                salary.getSalary_id(),
                salary.getEmployee_id(),
                salary.getDate(),
                salary.getAmount());
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public String generateSalaryId() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateId();
    }
}
