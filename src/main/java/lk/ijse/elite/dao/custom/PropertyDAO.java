package lk.ijse.elite.dao.custom;

import lk.ijse.elite.entity.Property;
import lk.ijse.elite.model.dto.PropertyDTO;

import java.sql.SQLException;

public interface PropertyDAO extends CrudDAO<Property> {
    boolean updatePropertyStatus(String id) throws SQLException, ClassNotFoundException;
}
