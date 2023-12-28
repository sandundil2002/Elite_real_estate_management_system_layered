package lk.ijse.elite.bo.custom;

import lk.ijse.elite.bo.SuperBO;
import lk.ijse.elite.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDTO> loadAllCustomer() throws SQLException,ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException,ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException,ClassNotFoundException;

    CustomerDTO searchCustomer(String id) throws SQLException,ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException,ClassNotFoundException;

    String generateCustomerId() throws SQLException,ClassNotFoundException;
}
