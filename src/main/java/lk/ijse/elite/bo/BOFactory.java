package lk.ijse.elite.bo;

import lk.ijse.elite.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        ADMIN,AGENT,CUSTOMER,DASHBOARD,EMPLOYEE,MAINTAIN,PAYMENT,PROPERTY,RENTING,SALARY,SCHEDULE,SELLORDER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ADMIN:
                return new AdminBOImpl();
            case AGENT:
                return new AgentBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case MAINTAIN:
                return new MaintainBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PROPERTY:
                return new PropertyBOImpl();
            case RENTING:
                return new RentingBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case SCHEDULE:
                return new ScheduleBOImpl();
            case SELLORDER:
                return new SellOrderBOImpl();
            default:
                return null;
        }
    }
}
