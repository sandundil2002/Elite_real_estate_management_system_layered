package lk.ijse.elite.dao.custom;

import lk.ijse.elite.dao.CrudDAO;
import lk.ijse.elite.entity.Maintain;

import java.sql.SQLException;

public interface MaintainDAO extends CrudDAO<Maintain> {
    boolean updateMaintainComplete(String maintainId) throws SQLException, ClassNotFoundException;

    boolean updateMaintainCansel(String maintainId) throws SQLException, ClassNotFoundException;
}
