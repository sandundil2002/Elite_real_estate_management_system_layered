package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.ScheduleBO;
import lk.ijse.elite.model.dto.ScheduleDTO;

import java.sql.SQLException;
import java.util.List;

public class ScheduleBOImpl implements ScheduleBO {
    @Override
    public List<ScheduleDTO> loadAllSchedule() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveSchedule(ScheduleDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateSchedule(ScheduleDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ScheduleDTO searchSchedule(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteSchedule(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateScheduleId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateScheduleCompleted(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateScheduleCansel(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
