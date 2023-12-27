package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.model.dto.RentDTO;

import java.sql.SQLException;
import java.util.List;

public interface RentingBO extends SuperBO {

    List<RentDTO> loadAllRenting() throws SQLException,ClassNotFoundException;

    boolean saveRenting(RentDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateRenting(RentDTO dto) throws SQLException,ClassNotFoundException;

    RentDTO searchRenting(String id) throws SQLException,ClassNotFoundException;

    boolean deleteRenting(String id) throws SQLException,ClassNotFoundException;

    String generateRentingId() throws SQLException,ClassNotFoundException;

    boolean isUpdated(Rent rentDto , Renting rentingDto, RentingDetail rentDetailDto, Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException;
}
