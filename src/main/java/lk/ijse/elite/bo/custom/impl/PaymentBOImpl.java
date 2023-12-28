package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.PaymentBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.PaymentDAO;
import lk.ijse.elite.entity.Payment;
import lk.ijse.elite.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public List<PaymentDTO> loadAllPayment() throws SQLException, ClassNotFoundException {
        List<Payment> payments = paymentDAO.loadAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments){
            paymentDTOS.add(new PaymentDTO(payment.getPayment_id(),payment.getCustomer_id(),payment.getProperty_id(),payment.getDate(),payment.getMethod(),payment.getPrice()));
        }
        return paymentDTOS;
    }

    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPayment_id(),dto.getCustomer_id(),dto.getProperty_id(),dto.getDate(),dto.getMethod(),dto.getPrice()));
    }
    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPayment_id(),dto.getCustomer_id(),dto.getProperty_id(),dto.getDate(),dto.getMethod(),dto.getPrice()));
    }

    @Override
    public PaymentDTO searchPayment(String id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.search(id);
        PaymentDTO paymentDTO = new PaymentDTO(payment.getPayment_id(),payment.getCustomer_id(),payment.getProperty_id(),payment.getDate(),payment.getMethod(),payment.getMethod());
        return paymentDTO;
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public String generatePaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateId();
    }
}
