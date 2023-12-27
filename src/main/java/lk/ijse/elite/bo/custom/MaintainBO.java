package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.model.dto.MaintainDTO;

import java.sql.SQLException;
import java.util.List;

public interface MaintainBO extends SuperBO {
    List<MaintainDTO> loadAllMaintain() throws SQLException,ClassNotFoundException;

    boolean saveMaintain(MaintainDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateMaintain(MaintainDTO dto) throws SQLException,ClassNotFoundException;

    MaintainDTO searchMaintain(String id) throws SQLException,ClassNotFoundException;

    boolean deleteMaintain(String id) throws SQLException,ClassNotFoundException;

    String generateMaintainId() throws SQLException,ClassNotFoundException;

    boolean updateMaintainComplete(String maintainId) throws SQLException, ClassNotFoundException;

    boolean updateMaintainCansel(String maintainId) throws SQLException, ClassNotFoundException;
}
