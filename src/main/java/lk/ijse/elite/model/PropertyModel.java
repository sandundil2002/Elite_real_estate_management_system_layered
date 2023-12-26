package lk.ijse.elite.model;

import lk.ijse.elite.model.dto.PropertyDTO;
import lk.ijse.elite.util.SQLUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyModel {
    public static List<PropertyDTO> loadAllProperty() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM property");
        List<PropertyDTO> proList = new ArrayList<>();

        while (resultSet.next()) {
            proList.add(new PropertyDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(5),
                    resultSet.getString(4),
                    resultSet.getString(3),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return proList;
    }

    public boolean saveProperty(PropertyDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO property VALUES (?,?,?,?,?,?,?)", dto.getPropertyId(), dto.getAgentId(), dto.getPrice(), dto.getAddress(), dto.getType(), dto.getPerches(), dto.getStatus());
    }

    public boolean updateProperty(PropertyDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("UPDATE property SET Agent_id = ?, Price = ?, Address = ?, Type = ?, Perches = ?, Status = ? WHERE Property_id = ?", dto.getAgentId(), dto.getPrice(), dto.getAddress(), dto.getType(), dto.getPerches(), dto.getStatus(), dto.getPropertyId());
    }

    public static boolean updatePropertyStatus(String propertyId) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("UPDATE property SET Status=? WHERE property_id=?", "Not Available", propertyId);
    }

    public static PropertyDTO searchProperty(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM property WHERE Property_id = ?", id);

        if (resultSet.next()){
            String pro_id = resultSet.getString(1);
            String a_id = resultSet.getString(2);
            String price = resultSet.getString(3);
            String address = resultSet.getString(4);
            String type = resultSet.getString(5);
            String perches = resultSet.getString(6);
            String status = resultSet.getString(7);
            return new PropertyDTO(pro_id,a_id,price, address, type, perches, status);
        }
        return null;
    }

    public boolean deleteProperty(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("DELETE FROM property WHERE Property_id = ?", id);
    }

    public String generatePropertyId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT Property_id FROM property ORDER BY Property_id DESC LIMIT 1");
        if(resultSet.next()){
            String id = resultSet.getString("Property_id");
            String numericPart = id.replaceAll("\\D","");
            int newPropertyId = Integer.parseInt(numericPart) + 1;
            return String.format("P%03d", newPropertyId);
        } else {
            return "P001";
        }
    }
}
