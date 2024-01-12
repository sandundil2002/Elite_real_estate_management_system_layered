package lk.ijse.elite.dao.custom;

import lk.ijse.elite.dao.CrudDAO;
import lk.ijse.elite.entity.Schedule;

import java.sql.SQLException;

public interface ScheduleDAO extends CrudDAO<Schedule> {
    boolean updateScheduleCompleted(String id) throws SQLException, ClassNotFoundException;

    boolean updateScheduleCansel(String id) throws SQLException, ClassNotFoundException;
}
