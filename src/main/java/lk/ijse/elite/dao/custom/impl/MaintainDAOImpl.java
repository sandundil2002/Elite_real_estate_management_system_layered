package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.MaintainDAO;
import lk.ijse.elite.entity.Maintain;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintainDAOImpl implements MaintainDAO {
    @Override
    public List<Maintain> loadAll() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM maintain");
        List<Maintain> maintainList = new ArrayList<>();

        while (resultSet.next()) {
            Maintain maintainDto = new Maintain(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
            maintainList.add(maintainDto);
        }
        return maintainList;
    }

    @Override
    public boolean save(Maintain dto) throws SQLException{
        return SQLUtil.sql("INSERT INTO maintain VALUES (?,?,?,?)",
                dto.getMaintain_id(),
                dto.getRent_id(),
                dto.getDate(),
                dto.getStatus());
    }

    @Override
    public boolean updateMaintainComplete(String maintainId) throws SQLException{
        return SQLUtil.sql("UPDATE maintain SET Status = ? WHERE Maintain_id = ?","Completed",maintainId);
    }

    @Override
    public boolean updateMaintainCansel(String maintainId) throws SQLException{
        return SQLUtil.sql("UPDATE maintain SET Status = ? WHERE Maintain_id = ?","Canceled",maintainId);
    }

    @Override
    public String generateId() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT Maintain_id FROM maintain ORDER BY Maintain_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Maintain_id");
            String numericPart = id.replaceAll("\\D", "");
            int newmaintainId = Integer.parseInt(numericPart);
            return String.format("M%03d", newmaintainId) + 1 ;
        } else {
            return "M001";
        }
    }

    @Override
    public boolean update(Maintain dto) throws SQLException{
        return false;
    }

    @Override
    public Maintain search(String id) throws SQLException{
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException{
        return false;
    }
}
