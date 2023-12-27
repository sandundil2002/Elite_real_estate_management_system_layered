package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.SalaryBO;
import lk.ijse.elite.model.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    @Override
    public List<SalaryDTO> loadAllSalary() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSalary(SalaryDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateSalaryId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
