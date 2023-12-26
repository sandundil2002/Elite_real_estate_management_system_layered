package lk.ijse.elite.model;

import javafx.scene.control.Alert;
import lk.ijse.elite.model.dto.PaymentDTO;
import lk.ijse.elite.model.dto.PaymentdetailDTO;
import lk.ijse.elite.util.TransactionUtil;
import java.sql.SQLException;
import static lk.ijse.elite.model.PaymentDetailModel.savePaymentDetail;
import static lk.ijse.elite.model.PaymentModel.savePayment;
import static lk.ijse.elite.model.PropertyModel.updatePropertyStatus;

public class PlaceOrderModel {
    /*public static boolean isUpdated(PaymentDTO paymentDto, PaymentdetailDTO paymentdetailDto) throws SQLException, ClassNotFoundException {
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
    }*/
}

