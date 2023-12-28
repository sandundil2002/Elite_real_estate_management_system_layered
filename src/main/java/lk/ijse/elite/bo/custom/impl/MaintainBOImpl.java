package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.MaintainBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.MaintainDAO;
import lk.ijse.elite.entity.Maintain;
import lk.ijse.elite.dto.MaintainDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintainBOImpl implements MaintainBO {
    MaintainDAO maintainDAO = (MaintainDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MAINTAIN);
    @Override
    public List<MaintainDTO> loadAllMaintain() throws SQLException, ClassNotFoundException {
        List<Maintain> maintains = maintainDAO.loadAll();
        List<MaintainDTO> maintainDTOS = new ArrayList<>();

        for (Maintain maintain : maintains){
            maintainDTOS.add(new MaintainDTO(maintain.getMaintain_id(),maintain.getRent_id(),maintain.getDate(),maintain.getStatus()));
        }
        return maintainDTOS;
    }

    @Override
    public boolean saveMaintain(MaintainDTO dto) throws SQLException, ClassNotFoundException {
        return maintainDAO.save(new Maintain(dto.getMaintain_id(),dto.getRent_id(),dto.getDate(),dto.getStatus()));
    }

    @Override
    public boolean updateMaintain(MaintainDTO dto) throws SQLException, ClassNotFoundException {
        return maintainDAO.update(new Maintain(dto.getMaintain_id(),dto.getRent_id(),dto.getDate(),dto.getStatus()));
    }

    @Override
    public MaintainDTO searchMaintain(String id) throws SQLException, ClassNotFoundException {
        Maintain maintain = maintainDAO.search(id);
        MaintainDTO maintainDTO = new MaintainDTO(maintain.getMaintain_id(),maintain.getRent_id(),maintain.getDate(),maintain.getStatus());
        return maintainDTO;
    }

    @Override
    public boolean deleteMaintain(String id) throws SQLException, ClassNotFoundException {
        return maintainDAO.delete(id);
    }

    @Override
    public String generateMaintainId() throws SQLException, ClassNotFoundException {
        return maintainDAO.generateId();
    }

    @Override
    public boolean updateMaintainComplete(String maintainId) throws SQLException, ClassNotFoundException {
        return maintainDAO.updateMaintainComplete(maintainId);
    }

    @Override
    public boolean updateMaintainCansel(String maintainId) throws SQLException, ClassNotFoundException {
        return maintainDAO.updateMaintainCansel(maintainId);
    }
}
