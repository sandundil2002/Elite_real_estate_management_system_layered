package lk.ijse.elite.bo.custom.impl;

import lk.ijse.elite.bo.custom.CustomerBO;
import lk.ijse.elite.dao.DAOFactory;
import lk.ijse.elite.dao.custom.CustomerDAO;
import lk.ijse.elite.entity.Customer;
import lk.ijse.elite.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public List<CustomerDTO> loadAllCustomer() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.loadAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : customers){
            customerDTOS.add(new CustomerDTO(
                    customer.getCustomer_id(),
                    customer.getShedule_id(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getMobile(),
                    customer.getEmail()));
        }
        return customerDTOS;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(
                dto.getCustomer_id(),
                dto.getShedule_id(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getEmail()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(
                dto.getCustomer_id(),
                dto.getShedule_id(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getEmail()));
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDTO(
                customer.getCustomer_id(),
                customer.getShedule_id(),
                customer.getName(),
                customer.getAddress(),
                customer.getMobile(),
                customer.getEmail());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateId();
    }
}
