package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.SellOrderBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.PaymentDAO;
import lk.ijse.elite.entity.Payment;
import lk.ijse.elite.entity.PaymentDetail;

import java.sql.SQLException;

public class SellOrderBOImpl implements SellOrderBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean isOrderSuccess(Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException, ClassNotFoundException {
        return paymentDAO.isPaymentSuccess(paymentDto,paymentdetailDto);
    }
}
