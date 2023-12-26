package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.CustomerDAO;
import lk.ijse.elite.entity.Customer;
import lk.ijse.elite.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> loadAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM customer");
        List<Customer> customerList = new ArrayList<>();

        while (resultSet.next()) {
            Customer customerDto = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            customerList.add(customerDto);
        }
        return customerList;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("INSERT INTO customer VALUES (?,?,?,?,?,?)",
                dto.getCustomer_id(),
                dto.getShedule_id(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getEmail());
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("UPDATE customer SET Shedule_id=?, Name=?, Address=?, Mobile=?, Email=? WHERE Customer_id=?",
                dto.getShedule_id(),
                dto.getName(),
                dto.getAddress(),
                dto.getMobile(),
                dto.getEmail(),
                dto.getCustomer_id());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT * FROM customer WHERE Customer_id=?", id);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.sql("DELETE FROM customer WHERE Customer_id=?", id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT Customer_id FROM customer ORDER BY Customer_id DESC LIMIT 1");
        if (resultSet.next()) {
            String id = resultSet.getString("Customer_id");
            String numericPart = id.replaceAll("\\D", "");
            int newCustomerId = Integer.parseInt(numericPart) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "C001";
        }
    }
}
