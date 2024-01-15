package lk.ijse.elite.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.elite.bo.custom.RentingBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.*;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.dto.RentDTO;
import lk.ijse.elite.dto.RentingDTO;
import lk.ijse.elite.util.TransactionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentingBOImpl implements RentingBO {
    RentingDAO rentingDAO = (RentingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RENTING);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    PaymentDetailDAO paymentDetailDAO = (PaymentDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT_DETAIL);
    RentingDetailDAO rentingDetailDAO = (RentingDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RENTING_DETAIL);
    PropertyDAO propertyDAO = (PropertyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROPERTY);
    @Override
    public List<RentingDTO> loadAllRenting() throws SQLException, ClassNotFoundException {
        List<Rent> rents = rentingDAO.loadAll();
        List<RentingDTO> rentDTOS = new ArrayList<>();

        for (Rent rent : rents){
            rentDTOS.add(new RentingDTO(
                    rent.getRentId(),
                    rent.getPropertyId(),
                    rent.getCustomerId(),
                    rent.getDate(),
                    rent.getDuration()));
        }
        return rentDTOS;
    }

    @Override
    public boolean saveRenting(RentDTO dto) throws SQLException, ClassNotFoundException {
        return rentingDAO.save(new Rent(
                dto.getRentId(),
                dto.getPropertyId(),
                dto.getCustomerId(),
                dto.getDate(),
                dto.getDuration()));
    }

    @Override
    public boolean updateRenting(RentDTO dto) throws SQLException, ClassNotFoundException {
        return rentingDAO.update(new Rent(
                dto.getRentId(),
                dto.getPropertyId(),
                dto.getCustomerId(),
                dto.getDate(),
                dto.getDuration()));
    }

    @Override
    public RentDTO searchRenting(String id) throws SQLException, ClassNotFoundException {
        Rent rent = rentingDAO.search(id);
        if (rent != null) {
            return new RentDTO(
                    rent.getRentId(),
                    rent.getPropertyId(),
                    rent.getCustomerId(),
                    rent.getDate(),
                    rent.getDuration());
        }
        return null;
    }

    @Override
    public boolean deleteRenting(String id) throws SQLException, ClassNotFoundException {
        return rentingDAO.delete(id);
    }

    @Override
    public String generateRentingId() throws SQLException, ClassNotFoundException {
        return rentingDAO.generateId();
    }


    @Override
    public boolean isRentOrderSuccess(Rent rentingDto, RentingDetail rentDetailDto, Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException {
        boolean result = false;
        try {
            boolean isPropertyAvailable = propertyDAO.isPropertyAvailable(paymentdetailDto.getProperty_id());
            if(!isPropertyAvailable) {
                new Alert(Alert.AlertType.ERROR, "Property is not available").show();
                return false;
            }
                TransactionUtil.startTransaction();
                boolean isPaymentSaved = paymentDAO.save(paymentDto);
                if (isPaymentSaved) {
                    boolean isPaymentDetailSaved = paymentDetailDAO.save(paymentdetailDto);
                    if (isPaymentDetailSaved) {
                        boolean isRentSaved = rentingDAO.save(rentingDto);
                        if (isRentSaved) {
                            boolean isRentDetailSaved = rentingDetailDAO.save(rentDetailDto);
                            if (isRentDetailSaved) {
                                boolean isPropertyUpdated = propertyDAO.updatePropertyStatus(rentingDto.getPropertyId());
                                if (isPropertyUpdated) {
                                    TransactionUtil.endTransaction();
                                    result = true;
                                }
                            }
                        }
                    }
                } else {
                TransactionUtil.rollBack();
            }
        } catch (SQLException | ClassNotFoundException e) {
            TransactionUtil.rollBack();
            e.printStackTrace();
        }
        return result ;
    }
}
