package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.DashboardDAO;
import lk.ijse.elite.entity.Today;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAOImpl implements DashboardDAO {
    @Override
    public List<Today> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT schedule.Shedule_id,customer.Name,Schedule.Time,customer.Mobile FROM customer JOIN Schedule ON customer.Shedule_id = Schedule.Shedule_id WHERE Schedule.Date = CURDATE() ORDER BY Schedule.Shedule_id ASC");
        List<Today> TodayList = new ArrayList<>();

        while (resultSet.next()) {
            Today todayDto = new Today(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
            TodayList.add(todayDto);
        }
        return TodayList;
    }

    @Override
    public int getTotalAppointmentsCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT COUNT(*) FROM schedule");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }

    @Override
    public int getTotalPropertiesCount() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT COUNT(*) FROM property");
        return resultSet.next() ? resultSet.getInt(1) : 0;
    }
    @Override
    public boolean save(Today dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Today dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Today search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
