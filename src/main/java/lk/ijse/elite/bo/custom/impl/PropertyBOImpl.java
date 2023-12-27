package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.PropertyBO;
import lk.ijse.elite.model.dto.PropertyDTO;

import java.sql.SQLException;
import java.util.List;

public class PropertyBOImpl implements PropertyBO {
    @Override
    public List<PropertyDTO> loadAllProperty() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveProperty(PropertyDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateProperty(PropertyDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public PropertyDTO searchProperty(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteProperty(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generatePropertyId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updatePropertyStatus(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
