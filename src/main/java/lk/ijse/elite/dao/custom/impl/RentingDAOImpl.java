package lk.ijse.elite.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.elite.bo.custom.PropertyBO;
import lk.ijse.elite.bo.custom.impl.PropertyBOImpl;
import lk.ijse.elite.dao.custom.RentingDAO;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.util.SQLUtil;
import lk.ijse.elite.util.TransactionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RentingDAOImpl implements RentingDAO {
    @Override
    public List<Rent> loadAll() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM renting");
        ObservableList<Rent> rentingList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            Rent rentDto = new Rent(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));
            rentingList.add(rentDto);
        }
        return rentingList;
    }

    @Override
    public boolean save(Rent dto) throws SQLException{
        return SQLUtil.sql("INSERT INTO renting VALUES(?,?,?,?,?)",
                dto.getRentId(),
                dto.getPropertyId(),
                dto.getCustomerId(),
                dto.getDate(),
                dto.getDuration()
        );
    }

    @Override
    public boolean update(Rent dto) throws SQLException{
        return false;
    }

    @Override
    public Rent search(String id) throws SQLException{
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException{
        return SQLUtil.sql("DELETE FROM renting WHERE rentId=?", id);
    }

    @Override
    public String generateId() throws SQLException{
        ResultSet resultSet = SQLUtil.sql("SELECT rent_id FROM renting ORDER BY rent_id DESC LIMIT 1");
        if (resultSet.next()){
            String id = resultSet.getString("rent_id");
            String numericPart = id.replaceAll("\\D","");
            int newRentId = Integer.parseInt(numericPart)+1;
            return String.format("R%03d",newRentId);
        } else {
            return "R001";
        }
    }
}
