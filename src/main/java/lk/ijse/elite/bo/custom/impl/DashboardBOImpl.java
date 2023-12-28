package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.DashboardBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.DashboardDAO;

import java.sql.SQLException;

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
}
