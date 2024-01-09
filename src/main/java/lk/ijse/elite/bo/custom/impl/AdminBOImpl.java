package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.AdminBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.AdminDAO;
import lk.ijse.elite.entity.Admin;
import lk.ijse.elite.dto.AdminDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public List<AdminDTO> loadAllAdmin() throws SQLException, ClassNotFoundException {
        List<Admin> admins = adminDAO.loadAll();
        List<AdminDTO> adminDTOS = new ArrayList<>();

        for (Admin admin : admins){
            adminDTOS.add(new AdminDTO(
                    admin.getAdmin_id(),
                    admin.getName(),
                    admin.getOtp(),
                    admin.getMobile(),
                    admin.getPassword(),
                    admin.getEmail()));
        }
        return adminDTOS;
    }

    @Override
    public boolean saveAdmin(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDAO.save(new Admin(
                dto.getAdmin_id(),
                dto.getName(),
                dto.getOtp(),
                dto.getMobile(),
                dto.getPassword(),
                dto.getEmail()));
    }

    @Override
    public boolean updateAdmin(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDAO.update(new Admin(
                dto.getAdmin_id(),
                dto.getName(),
                dto.getOtp(),
                dto.getMobile(),
                dto.getPassword(),
                dto.getEmail()));
    }

    @Override
    public AdminDTO searchAdmin(String id) throws SQLException, ClassNotFoundException {
        Admin admin = adminDAO.search(id);
        if (admin != null) {
            return new AdminDTO(
                    admin.getAdmin_id(),
                    admin.getName(),
                    admin.getOtp(),
                    admin.getMobile(),
                    admin.getPassword(),
                    admin.getPassword());
        }
        return null;
    }

    @Override
    public boolean deleteAdmin(String id) throws SQLException, ClassNotFoundException {
        return adminDAO.delete(id);
    }

    @Override
    public String generateAdminId() throws SQLException, ClassNotFoundException {
        return adminDAO.generateId();
    }

    @Override
    public boolean searchAdminPassword(String id) throws SQLException, ClassNotFoundException {
        return adminDAO.searchAdminPassword(id);
    }

    @Override
    public boolean searchAdminUserId(String id) throws SQLException, ClassNotFoundException {
        return adminDAO.searchAdminUserId(id);
    }
}
