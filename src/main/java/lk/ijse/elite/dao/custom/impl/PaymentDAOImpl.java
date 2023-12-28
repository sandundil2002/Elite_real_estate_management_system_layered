package lk.ijse.elite.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.elite.bo.custom.PropertyBO;
import lk.ijse.elite.bo.custom.impl.PropertyBOImpl;
import lk.ijse.elite.dao.custom.PaymentDAO;
import lk.ijse.elite.entity.Payment;
import lk.ijse.elite.entity.PaymentDetail;
import lk.ijse.elite.util.SQLUtil;
import lk.ijse.elite.util.TransactionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    PropertyBO propertyBO = new PropertyBOImpl();
    @Override
    public List<Payment> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM payment");
        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            Payment paymentDto = new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            paymentList.add(paymentDto);
        }
        return paymentList;
    }

    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO payment VALUES (?,?,?,?,?,?)",
                dto.getPayment_id(),
                dto.getCustomer_id(),
                dto.getProperty_id(),
                dto.getDate(),
                dto.getMethod(),
                dto.getPrice());
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT Payment_id FROM payment ORDER BY Payment_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id =  resultSet.getString(1);
            String numericPart = id.replaceAll("\\D", "");
            int newPaymentId = Integer.parseInt(numericPart) + 1;
            return String.format("Pay%03d", newPaymentId);
        } else {
            return "Pay001";
        }
    }

    @Override
    public boolean isPaymentSuccess(Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException, ClassNotFoundException {
        try{
            TransactionUtil.startTransaction();
            boolean isPaymentSaved = save(paymentDto);
            if(isPaymentSaved){
                boolean isPaymentDetailSaved = new PaymentDetailDAOImpl().save(paymentdetailDto);
                if (isPaymentDetailSaved){
                    boolean isPropertyUpdated = propertyBO.updatePropertyStatus(paymentdetailDto.getProperty_id());
                    if (isPropertyUpdated){
                        return true;
                    }
                }
            }
            TransactionUtil.rollBack();
            return false;
        } catch (SQLException e){
            TransactionUtil.rollBack();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            TransactionUtil.endTransaction();
        }
        return false;
    }
}
