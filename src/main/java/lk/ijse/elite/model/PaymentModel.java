package lk.ijse.elite.model;

import javafx.scene.control.Alert;
import lk.ijse.elite.model.dto.PaymentDTO;
import lk.ijse.elite.model.dto.PaymentdetailDTO;
import lk.ijse.elite.util.SQLUtil;
import lk.ijse.elite.util.TransactionUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static lk.ijse.elite.model.PaymentDetailModel.savePaymentDetail;
import static lk.ijse.elite.model.PropertyModel.updatePropertyStatus;

public class PaymentModel {
    public static boolean isPaymentSuccess(PaymentDTO paymentDto, PaymentdetailDTO paymentdetailDto) throws SQLException, ClassNotFoundException {
        try{
            TransactionUtil.startTransaction();
            boolean isPaymentSaved = savePayment(paymentDto);
            if(isPaymentSaved){
                boolean isPaymentDetailSaved = savePaymentDetail(paymentdetailDto);
                if (isPaymentDetailSaved){
                    boolean isPropertyUpdated = updatePropertyStatus(paymentdetailDto.getProperty_id());
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
    public List<PaymentDTO> getAllPayments() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM payment");
        List<PaymentDTO> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            paymentList.add(new PaymentDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return paymentList;
    }

    public static boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO payment VALUES (?,?,?,?,?,?)",dto.getPayment_id(),dto.getCustomer_id(),dto.getProperty_id(),dto.getDate(),dto.getMethod(),dto.getPrice());
    }

    public String generatePaymentId() throws SQLException, ClassNotFoundException {
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
}
