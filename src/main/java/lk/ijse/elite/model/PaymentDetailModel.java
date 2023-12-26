package lk.ijse.elite.model;

import lk.ijse.elite.model.dto.PaymentdetailDTO;
import lk.ijse.elite.util.SQLUtil;
import java.sql.SQLException;

public class PaymentDetailModel {
    public static boolean savePaymentDetail(PaymentdetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO payment_details VALUES (?,?,?)",dto.getProperty_id(),dto.getPayment_id(),dto.getMethod());
    }
}
