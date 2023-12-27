package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.PropertyDAO;
import lk.ijse.elite.entity.Property;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAOImpl implements PropertyDAO {
    @Override
    public List<Property> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM property");
        List<Property> proList = new ArrayList<>();

        while (resultSet.next()) {
            Property propertyDto = new Property(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(5),
                    resultSet.getString(4),
                    resultSet.getString(3),
                    resultSet.getString(6),
                    resultSet.getString(7));
            proList.add(propertyDto);
        }
        return proList;
    }

    @Override
    public boolean save(Property dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO property VALUES (?,?,?,?,?,?,?)",
                dto.getPropertyId(),
                dto.getAgentId(),
                dto.getPrice(),
                dto.getAddress(),
                dto.getType(),
                dto.getPerches(),
                dto.getStatus());
    }

    @Override
    public boolean update(Property dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("UPDATE property SET Agent_id = ?, Price = ?, Address = ?, Type = ?, Perches = ?, Status = ? WHERE Property_id = ?",
                dto.getAgentId(),
                dto.getPrice(),
                dto.getAddress(),
                dto.getType(),
                dto.getPerches(),
                dto.getStatus(),
                dto.getPropertyId());
    }

    @Override
    public Property search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM property WHERE Property_id = ?", id);

        if (resultSet.next()){
            String pro_id = resultSet.getString(1);
            String a_id = resultSet.getString(2);
            String price = resultSet.getString(3);
            String address = resultSet.getString(4);
            String type = resultSet.getString(5);
            String perches = resultSet.getString(6);
            String status = resultSet.getString(7);
            return new Property(pro_id,a_id,price, address, type, perches, status);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("DELETE FROM property WHERE Property_id = ?", id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean updatePropertyStatus(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("UPDATE property SET Status=? WHERE property_id=?", "Not Available", id);    }
}
