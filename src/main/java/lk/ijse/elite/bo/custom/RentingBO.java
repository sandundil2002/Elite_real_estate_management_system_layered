package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.dto.RentDTO;
import lk.ijse.elite.dto.RentingDTO;

import java.sql.SQLException;
import java.util.List;

public interface RentingBO extends SuperBO {

    List<RentingDTO> loadAllRenting() throws SQLException,ClassNotFoundException;

    boolean saveRenting(RentDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateRenting(RentDTO dto) throws SQLException,ClassNotFoundException;

    RentDTO searchRenting(String id) throws SQLException,ClassNotFoundException;

    boolean deleteRenting(String id) throws SQLException,ClassNotFoundException;

    String generateRentingId() throws SQLException,ClassNotFoundException;

    boolean isUpdated(Rent rentDto, RentingDetail rentDetailDto, Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException;
}
