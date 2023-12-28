package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.PropertyBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.PropertyDAO;
import lk.ijse.elite.entity.Property;
import lk.ijse.elite.dto.PropertyDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyBOImpl implements PropertyBO {
    PropertyDAO propertyDAO = (PropertyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROPERTY);
    @Override
    public List<PropertyDTO> loadAllProperty() throws SQLException, ClassNotFoundException {
        List<Property> properties = propertyDAO.loadAll();
        List<PropertyDTO> propertyDTOS = new ArrayList<>();

        for (Property property : properties){
            propertyDTOS.add(new PropertyDTO(property.getPropertyId(),property.getAgentId(),property.getPrice(),property.getAddress(),property.getType(),property.getStatus(),property.getPerches()));
        }
        return propertyDTOS;
    }

    @Override
    public boolean saveProperty(PropertyDTO dto) throws SQLException, ClassNotFoundException {
        return propertyDAO.save(new Property(dto.getPropertyId(),dto.getAgentId(),dto.getPrice(),dto.getAddress(),dto.getType(),dto.getStatus(),dto.getPerches()));
    }

    @Override
    public boolean updateProperty(PropertyDTO dto) throws SQLException, ClassNotFoundException {
        return propertyDAO.update(new Property(dto.getPropertyId(),dto.getAgentId(),dto.getPrice(),dto.getAddress(),dto.getType(),dto.getStatus(),dto.getPerches()));
    }

    @Override
    public PropertyDTO searchProperty(String id) throws SQLException, ClassNotFoundException {
        Property property = propertyDAO.search(id);
        PropertyDTO propertyDTO = new PropertyDTO(property.getPropertyId(),property.getAgentId(),property.getPrice(),property.getAddress(),property.getType(),property.getStatus(),property.getPerches());
        return propertyDTO;
    }

    @Override
    public boolean deleteProperty(String id) throws SQLException, ClassNotFoundException {
        return propertyDAO.delete(id);
    }

    @Override
    public String generatePropertyId() throws SQLException, ClassNotFoundException {
        return propertyDAO.generateId();
    }

    @Override
    public boolean updatePropertyStatus(String id) throws SQLException, ClassNotFoundException {
        return propertyDAO.updatePropertyStatus(id);
    }
}
