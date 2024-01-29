package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.AdminDAO;
import lk.ijse.elite.entity.Admin;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public List<Admin> loadAll() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM admin");
        List<Admin> adminList = new ArrayList<>();

        while (resultSet.next()) {
          Admin adminDto = new Admin(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(5),
                    resultSet.getString(4),
                    resultSet.getString(3),
                    resultSet.getString(6));
          adminList.add(adminDto);
        }
        return adminList;
    }

    @Override
    public boolean save(Admin dto) throws SQLException{
        return SQLUtil.sql("INSERT INTO admin VALUES (?,?,?,?,?,?)",
                dto.getAdmin_id(),
                dto.getName(),
                dto.getOtp(),
                dto.getMobile(),
                dto.getPassword(),
                dto.getEmail());
    }

    public boolean searchAdminPassword(String string) throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM admin WHERE password=?", string);
        return resultSet.next();
    }

    public boolean searchAdminUserId(String string) throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM admin WHERE Admin_id=?", string);
        return resultSet.next();
    }

    @Override
    public Admin search(String id) throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM admin WHERE Admin_id=?",id);
        if (resultSet.next())
            return new Admin(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException{
        return false;
    }

    @Override
    public boolean update(Admin dto) throws SQLException{
        return false;
    }

    @Override
    public String generateId() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT Admin_id FROM admin ORDER BY Admin_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Admin_id");
            String numericPart = id.replaceAll("\\D", "");
            int newAdminId = Integer.parseInt(numericPart) + 1;
            return String.format("A%03d", newAdminId);
        } else {
            return "A001";
        }
    }
}
