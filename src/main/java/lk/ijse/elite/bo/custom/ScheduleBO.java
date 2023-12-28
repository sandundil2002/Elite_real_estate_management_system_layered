package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.dto.ScheduleDTO;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleBO extends SuperBO {

    List<ScheduleDTO> loadAllSchedule() throws SQLException,ClassNotFoundException;

    boolean saveSchedule(ScheduleDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateSchedule(ScheduleDTO dto) throws SQLException,ClassNotFoundException;

    ScheduleDTO searchSchedule(String id) throws SQLException,ClassNotFoundException;

    boolean deleteSchedule(String id) throws SQLException,ClassNotFoundException;

    String generateScheduleId() throws SQLException,ClassNotFoundException;

    boolean updateScheduleCompleted(String id) throws SQLException, ClassNotFoundException;

    boolean updateScheduleCansel(String id) throws SQLException, ClassNotFoundException;
}
