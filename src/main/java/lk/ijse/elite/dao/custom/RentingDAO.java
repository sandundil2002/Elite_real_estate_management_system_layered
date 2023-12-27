package lk.ijse.elite.dao.custom;

import lk.ijse.elite.entity.*;
import java.sql.SQLException;

public interface RentingDAO extends CrudDAO<Rent> {
    boolean isUpdated(Rent rentDto , Renting rentingDto, RentingDetail rentDetailDto, Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException;
}
