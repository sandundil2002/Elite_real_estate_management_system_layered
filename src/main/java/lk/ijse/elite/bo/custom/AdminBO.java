package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.dto.AdminDTO;

import java.sql.SQLException;
import java.util.List;

public interface AdminBO extends SuperBO {

    List<AdminDTO> loadAllAdmin() throws SQLException,ClassNotFoundException;

    boolean saveAdmin(AdminDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateAdmin(AdminDTO dto) throws SQLException,ClassNotFoundException;

    AdminDTO searchAdmin(String id) throws SQLException,ClassNotFoundException;

    boolean deleteAdmin(String id) throws SQLException,ClassNotFoundException;

    String generateAdminId() throws SQLException,ClassNotFoundException;

    boolean searchAdminPassword(String string) throws SQLException, ClassNotFoundException;

    boolean searchAdminUserId(String string) throws SQLException, ClassNotFoundException;
}
