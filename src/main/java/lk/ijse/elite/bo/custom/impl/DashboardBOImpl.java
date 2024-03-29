package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.DashboardBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.DashboardDAO;
import lk.ijse.elite.dto.CustomerDTO;
import lk.ijse.elite.entity.Customer;
import lk.ijse.elite.entity.Today;
import lk.ijse.elite.dto.TodayAppoinmentsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardBOImpl implements DashboardBO {
    DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DASHBOARD);
    @Override
    public int getTotalAppointmentsCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalAppointmentsCount();
    }

    @Override
    public int getTotalPropertiesCount() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalPropertiesCount();
    }

    @Override
    public List<TodayAppoinmentsDTO> loadTodayShedules() throws SQLException, ClassNotFoundException {
        List<Today> loadAll = dashboardDAO.loadAll();
        List<TodayAppoinmentsDTO> todayAppoinmentsDTOS = new ArrayList<>();

        for (Today today : loadAll){
            todayAppoinmentsDTOS.add(new TodayAppoinmentsDTO(
                    today.getShedule_id(),
                    today.getName(),
                    today.getTime(),
                    today.getMobile()));
        }
        return todayAppoinmentsDTOS;
    }
}
