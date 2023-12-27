package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.entity.Payment;
import lk.ijse.elite.entity.PaymentDetail;

import java.sql.SQLException;

public interface SellOrderBO extends SuperBO {
    boolean isOrderSuccess(Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException, ClassNotFoundException;
}
