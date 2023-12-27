package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.RentingDetailDAO;
import lk.ijse.elite.entity.RentingDetail;
import lk.ijse.elite.util.SQLUtil;

import java.sql.SQLException;
import java.util.List;

public class RentingDetailDAOImpl implements RentingDetailDAO {
    @Override
    public List<RentingDetail> loadAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(RentingDetail dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO renting_details VALUES(?,?,?)",
                dto.getRentId(),
                dto.getPropertyId(),
                dto.getDescription());
    }

    @Override
    public boolean update(RentingDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RentingDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
