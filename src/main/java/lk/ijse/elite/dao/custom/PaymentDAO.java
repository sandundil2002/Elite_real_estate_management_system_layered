package lk.ijse.elite.dao.custom;

import lk.ijse.elite.entity.Payment;
import lk.ijse.elite.entity.PaymentDetail;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    boolean isPaymentSuccess(Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException, ClassNotFoundException;
}
