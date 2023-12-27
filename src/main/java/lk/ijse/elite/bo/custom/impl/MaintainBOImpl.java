package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.MaintainBO;
import lk.ijse.elite.model.dto.MaintainDTO;

import java.sql.SQLException;
import java.util.List;

public class MaintainBOImpl implements MaintainBO {
    @Override
    public List<MaintainDTO> loadAllMaintain() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveMaintain(MaintainDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateMaintain(MaintainDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public MaintainDTO searchMaintain(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteMaintain(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateMaintainId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateMaintainComplete(String maintainId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateMaintainCansel(String maintainId) throws SQLException, ClassNotFoundException {
        return false;
    }
}
