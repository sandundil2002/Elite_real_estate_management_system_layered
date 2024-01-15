package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.dto.PropertyDTO;

import java.sql.SQLException;
import java.util.List;

public interface PropertyBO extends SuperBO {

    List<PropertyDTO> loadAllProperty() throws SQLException,ClassNotFoundException;

    boolean saveProperty(PropertyDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateProperty(PropertyDTO dto) throws SQLException,ClassNotFoundException;

    PropertyDTO searchProperty(String id) throws SQLException,ClassNotFoundException;

    boolean deleteProperty(String id) throws SQLException,ClassNotFoundException;

    String generatePropertyId() throws SQLException,ClassNotFoundException;

    boolean updatePropertyStatus(String id) throws SQLException, ClassNotFoundException;
    boolean isPropertyAvailable(String id) throws SQLException, ClassNotFoundException;
}
