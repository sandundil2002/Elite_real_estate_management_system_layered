package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.AdminBO;
import lk.ijse.elite.model.dto.AdminDTO;

import java.sql.SQLException;
import java.util.List;

public class AdminBOImpl implements AdminBO {
    @Override
    public List<AdminDTO> loadAllAdmin() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveAdmin(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateAdmin(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AdminDTO searchAdmin(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteAdmin(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateAdminId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean searchAdminPassword(String string) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean searchAdminUserId(String string) throws SQLException, ClassNotFoundException {
        return false;
    }
}
