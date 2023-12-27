package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.RentingBO;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.model.dto.RentDTO;

import java.sql.SQLException;
import java.util.List;

public class RentingBOImpl implements RentingBO {
    @Override
    public List<RentDTO> loadAllRenting() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveRenting(RentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRenting(RentDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RentDTO searchRenting(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean deleteRenting(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateRentingId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean isUpdated(Rent rentDto, Renting rentingDto, RentingDetail rentDetailDto, Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException {
        return false;
    }
}
