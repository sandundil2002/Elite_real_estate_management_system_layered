package lk.ijse.elite.dao.custom;

import lk.ijse.elite.dao.CrudDAO;
import lk.ijse.elite.entity.Today;

import java.sql.SQLException;

public interface DashboardDAO extends CrudDAO<Today> {
    int getTotalAppointmentsCount() throws SQLException, ClassNotFoundException;

    int getTotalPropertiesCount() throws SQLException, ClassNotFoundException;
}
