package lk.ijse.elite.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.elite.dao.custom.RentingDAO;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.util.SQLUtil;
import lk.ijse.elite.util.TransactionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static lk.ijse.elite.model.PropertyModel.updatePropertyStatus;

public class RentingDAOImpl implements RentingDAO {
    @Override
    public List<Rent> loadAll() throws SQLException, ClassNotFoundException {
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
    public boolean save(Rent dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO renting VALUES(?,?,?,?,?)",
                dto.getRentId(),
                dto.getPropertyId(),
                dto.getCustomerId(),
                dto.getDate(),
                dto.getDuration()
        );
    }

    @Override
    public boolean update(Rent dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Rent search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("DELETE FROM renting WHERE rentId=?", id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean isUpdated(Rent rentDto , Renting rentingDto, RentingDetail rentDetailDto, Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException {
        try {
            TransactionUtil.startTransaction();
            boolean isPaymentSaved = new PaymentDAOImpl().save(paymentDto);
            if(isPaymentSaved){
                boolean isPaymentDetailSaved = new PaymentDetailDAOImpl().save(paymentdetailDto);
                if(isPaymentDetailSaved){
                    boolean isRentSaved = new RentingDAOImpl().save(rentDto);
                    if(isRentSaved){
                        boolean isRentDetailSaved = new RentingDetailDAOImpl().save(rentDetailDto);
                        if(isRentDetailSaved){
                            boolean isPropertyUpdated = updatePropertyStatus(rentDto.getPropertyId());
                            if(isPropertyUpdated){
                                return true;
                            }
                        }
                    }
                }
            }
            TransactionUtil.rollBack();
            return false;
        } catch (ClassNotFoundException e) {
            TransactionUtil.rollBack();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            TransactionUtil.endTransaction();
        }
        return false;
    }
}
