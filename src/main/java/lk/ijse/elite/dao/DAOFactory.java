package lk.ijse.elite.dao;

import lk.ijse.elite.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADMIN,AGENT,CUSTOMER,DASHBOARD,EMPLOYEE,MAINTAIN,PAYMENT,PAYMENT_DETAIL,PROPERTY,RENTING,RENTING_DETAIL,SALARY,SCHEDULE
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ADMIN:
                return new AdminDAOImpl();
            case AGENT:
                return new AgentDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DASHBOARD:
                return new DashboardDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MAINTAIN:
                return new MaintainDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PAYMENT_DETAIL:
                return new PaymentDetailDAOImpl();
            case PROPERTY:
                return new PropertyDAOImpl();
            case RENTING:
                return new RentingDAOImpl();
            case RENTING_DETAIL:
                return new RentingDetailDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case SCHEDULE:
                return new ScheduleDAOImpl();
            default:
                return null;
        }
    }
}
