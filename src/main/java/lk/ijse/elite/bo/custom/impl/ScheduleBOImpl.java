package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.ScheduleBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.ScheduleDAO;
import lk.ijse.elite.entity.Schedule;
import lk.ijse.elite.model.dto.ScheduleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SCHEDULE);
    @Override
    public List<ScheduleDTO> loadAllSchedule() throws SQLException, ClassNotFoundException {
        List<Schedule> schedules = scheduleDAO.loadAll();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule : schedules){
            scheduleDTOS.add(new ScheduleDTO(schedule.getScheduleId(),schedule.getAdminId(),schedule.getDate(),schedule.getTime(),schedule.getStatus()));
        }
        return scheduleDTOS;
    }

    @Override
    public boolean saveSchedule(ScheduleDTO dto) throws SQLException, ClassNotFoundException {
        return scheduleDAO.save(new Schedule(dto.getScheduleId(),dto.getAdminId(),dto.getDate(),dto.getTime(),dto.getStatus()));
    }

    @Override
    public boolean updateSchedule(ScheduleDTO dto) throws SQLException, ClassNotFoundException {
        return scheduleDAO.update(new Schedule(dto.getScheduleId(),dto.getAdminId(),dto.getDate(),dto.getTime(),dto.getStatus()));
    }

    @Override
    public ScheduleDTO searchSchedule(String id) throws SQLException, ClassNotFoundException {
        Schedule schedule = scheduleDAO.search(id);
        ScheduleDTO scheduleDTO = new ScheduleDTO(schedule.getScheduleId(),schedule.getAdminId(),schedule.getDate(),schedule.getTime(),schedule.getStatus());
        return scheduleDTO;
    }

    @Override
    public boolean deleteSchedule(String id) throws SQLException, ClassNotFoundException {
        return scheduleDAO.delete(id);
    }

    @Override
    public String generateScheduleId() throws SQLException, ClassNotFoundException {
        return scheduleDAO.generateId();
    }

    @Override
    public boolean updateScheduleCompleted(String id) throws SQLException, ClassNotFoundException {
        return scheduleDAO.updateScheduleCompleted(id);
    }

    @Override
    public boolean updateScheduleCansel(String id) throws SQLException, ClassNotFoundException {
        return scheduleDAO.updateScheduleCansel(id);
    }
}
