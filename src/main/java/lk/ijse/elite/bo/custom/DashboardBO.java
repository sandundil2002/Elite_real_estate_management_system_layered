package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.dto.TodayAppoinmentsDTO;
import lk.ijse.elite.entity.Today;

import java.sql.SQLException;
import java.util.List;

public interface DashboardBO extends SuperBO {
    int getTotalAppointmentsCount() throws SQLException, ClassNotFoundException;

    int getTotalPropertiesCount() throws SQLException, ClassNotFoundException;

    List<TodayAppoinmentsDTO> loadTodayShedules() throws SQLException, ClassNotFoundException;
}
