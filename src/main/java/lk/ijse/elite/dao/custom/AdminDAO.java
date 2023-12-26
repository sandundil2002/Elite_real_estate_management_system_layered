package lk.ijse.elite.dao.custom;

import lk.ijse.elite.entity.Admin;

import java.sql.SQLException;

public interface AdminDAO extends CrudDAO<Admin>{
    boolean searchAdminPassword(String string) throws SQLException, ClassNotFoundException;

    boolean searchAdminUserId(String string) throws SQLException, ClassNotFoundException;
}
