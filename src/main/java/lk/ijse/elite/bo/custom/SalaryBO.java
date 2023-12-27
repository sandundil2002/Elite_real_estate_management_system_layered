package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.model.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public interface SalaryBO extends SuperBO {

    List<SalaryDTO> loadAllSalary() throws SQLException,ClassNotFoundException;

    boolean saveSalary(SalaryDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateSalary(SalaryDTO dto) throws SQLException,ClassNotFoundException;

    SalaryDTO searchSalary(String id) throws SQLException,ClassNotFoundException;

    boolean deleteSalary(String id) throws SQLException,ClassNotFoundException;

    String generateSalaryId() throws SQLException,ClassNotFoundException;
}
