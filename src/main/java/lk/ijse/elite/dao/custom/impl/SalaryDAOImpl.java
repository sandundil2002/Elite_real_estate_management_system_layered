package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.SalaryDAO;
import lk.ijse.elite.entity.Salary;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public List<Salary> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Salary dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("insert into salary values(?,?,?,?)",
                dto.getSalary_id(),
                dto.getEmployee_id(),
                dto.getDate(),
                dto.getAmount());
    }

    @Override
    public boolean update(Salary dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("select salary_id from salary order by salary_id desc limit 1");
        if (resultSet.next()){
            String id = resultSet.getString("salary_id");
            String numericPart = id.replaceAll("\\D+","");
            int newSalaryId = Integer.parseInt(numericPart) + 1;
            return String.format("Sal%03d",newSalaryId);
        } else {
            return "Sal001";
        }
    }
}
