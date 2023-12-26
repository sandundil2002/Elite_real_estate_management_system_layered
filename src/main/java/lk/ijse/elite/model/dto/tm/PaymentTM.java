package lk.ijse.elite.model.dto.tm;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentTM {
    private String payment_id;
    private String property_id;
    private String customer_id;
    private String date;
    private String price;
    private String method;
}
