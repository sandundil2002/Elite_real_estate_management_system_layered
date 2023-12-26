package lk.ijse.elite.model;

import lk.ijse.elite.model.dto.RentingDetailDTO;
import lk.ijse.elite.util.SQLUtil;
import java.sql.SQLException;

public class RentingDetailModel {
    public static boolean saveRentingDetail(RentingDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO renting_details VALUES(?,?,?)",dto.getRentId(),dto.getPropertyId(),dto.getDescription());
    }
}
