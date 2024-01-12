package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.ScheduleDAO;
import lk.ijse.elite.entity.Schedule;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public List<Schedule> loadAll() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM schedule");
        List<Schedule> ScheduleList = new ArrayList<>();

        while (resultSet.next()) {
            Schedule scheduleDto = new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(4),
                    resultSet.getString(3),
                    resultSet.getString(2),
                    resultSet.getString(5));
            ScheduleList.add(scheduleDto);
        }
        return ScheduleList;
    }

    @Override
    public boolean save(Schedule dto) throws SQLException{
        return SQLUtil.sql("INSERT INTO schedule VALUES(?,?,?,?,?)",
                dto.getScheduleId(),
                dto.getAdminId(),
                dto.getDate(),
                dto.getTime(),
                dto.getStatus()
        );
    }

    @Override
    public boolean update(Schedule dto) throws SQLException{
        return SQLUtil.sql("UPDATE schedule SET Admin_id=?, Date=?, Time=?, Status=? WHERE Shedule_id=?",
                dto.getAdminId(),
                dto.getDate(),
                dto.getTime(),
                dto.getStatus(),
                dto.getScheduleId());
    }

    @Override
    public Schedule search(String id) throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM schedule WHERE Shedule_id=?", id);

        if (resultSet.next()) {
            String shedule_id = resultSet.getString(1);
            String admin_id = resultSet.getString(2);
            String date = resultSet.getString(3);
            String time = resultSet.getString(4);
            String status = resultSet.getString(5);
            return new Schedule(shedule_id, admin_id, date, time,status);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException{
        return SQLUtil.sql("DELETE FROM schedule WHERE Shedule_id=?", id);
    }

    @Override
    public String generateId() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT Shedule_id FROM schedule ORDER BY Shedule_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Shedule_id");
            String numericPart = id.replaceAll("\\D+","");
            int newSheduleId = Integer.parseInt(numericPart) + 1;
            return String.format("S%03d",newSheduleId);
        } else {
            return "S001";
        }
    }

    @Override
    public boolean updateScheduleCompleted(String id) throws SQLException{
        return SQLUtil.sql("UPDATE schedule SET Status = ? WHERE Shedule_id = ?", "Completed", id);
    }

    @Override
    public boolean updateScheduleCansel(String id) throws SQLException{
        return SQLUtil.sql("UPDATE schedule SET Status = ? WHERE Shedule_id = ?", "Cansel", id);
    }
}
