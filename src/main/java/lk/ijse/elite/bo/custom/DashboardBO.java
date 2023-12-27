package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import java.sql.SQLException;

public interface DashboardBO extends SuperBO {
    int getTotalAppointmentsCount() throws SQLException, ClassNotFoundException;

    int getTotalPropertiesCount() throws SQLException, ClassNotFoundException;
}
