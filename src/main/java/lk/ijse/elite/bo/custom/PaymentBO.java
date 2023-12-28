package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

    List<PaymentDTO> loadAllPayment() throws SQLException,ClassNotFoundException;

    boolean savePayment(PaymentDTO dto) throws SQLException,ClassNotFoundException;

    boolean updatePayment(PaymentDTO dto) throws SQLException,ClassNotFoundException;

    PaymentDTO searchPayment(String id) throws SQLException,ClassNotFoundException;

    boolean deletePayment(String id) throws SQLException,ClassNotFoundException;

    String generatePaymentId() throws SQLException,ClassNotFoundException;

}
