package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.PaymentBO;
import lk.ijse.elite.model.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    @Override
    public List<PaymentDTO> loadAllPayment() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generatePaymentId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
