package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.RentingBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.RentingDAO;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.model.dto.RentDTO;
import lk.ijse.elite.model.dto.RentingDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentingBOImpl implements RentingBO {
    RentingDAO rentingDAO = (RentingDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RENTING);
    @Override
    public List<RentingDTO> loadAllRenting() throws SQLException, ClassNotFoundException {
        List<Rent> rents = rentingDAO.loadAll();
        List<RentingDTO> rentDTOS = new ArrayList<>();

        for (Rent rent : rents){
            rentDTOS.add(new RentingDTO(rent.getRentId(),rent.getPropertyId(),rent.getCustomerId(),rent.getDate(),rent.getDuration()));
        }
        return rentDTOS;
    }

    @Override
    public boolean saveRenting(RentDTO dto) throws SQLException, ClassNotFoundException {
        return rentingDAO.save(new Rent(dto.getRentId(),dto.getPropertyId(),dto.getCustomerId(),dto.getDate(),dto.getDuration()));
    }

    @Override
    public boolean updateRenting(RentDTO dto) throws SQLException, ClassNotFoundException {
        return rentingDAO.update(new Rent(dto.getRentId(),dto.getPropertyId(),dto.getCustomerId(),dto.getDate(),dto.getDuration()));
    }

    @Override
    public RentDTO searchRenting(String id) throws SQLException, ClassNotFoundException {
        Rent rent = rentingDAO.search(id);
        RentDTO rentDTO = new RentDTO(rent.getRentId(),rent.getPropertyId(),rent.getCustomerId(),rent.getDate(),rent.getDuration());
        return rentDTO;
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
    public boolean isUpdated(Rent rentingDto, RentingDetail rentDetailDto, Payment paymentDto, PaymentDetail paymentdetailDto) throws SQLException {
        return rentingDAO.isUpdated(rentingDto,rentDetailDto,paymentDto,paymentdetailDto);
    }
}
