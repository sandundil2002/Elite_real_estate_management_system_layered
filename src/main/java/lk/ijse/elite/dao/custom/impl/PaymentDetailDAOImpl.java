package lk.ijse.elite.dao.custom.impl;

import lk.ijse.elite.dao.custom.PaymentDetailDAO;
import lk.ijse.elite.entity.PaymentDetail;
import lk.ijse.elite.util.SQLUtil;

import java.sql.SQLException;
import java.util.List;

public class PaymentDetailDAOImpl implements PaymentDetailDAO {
    @Override
    public List<PaymentDetail> loadAll() throws SQLException{
        return null;
    }

    @Override
    public boolean save(PaymentDetail dto) throws SQLException{
        return SQLUtil.sql("INSERT INTO payment_details VALUES (?,?,?)",
                dto.getProperty_id(),
                dto.getPayment_id(),
                dto.getMethod());
    }

    @Override
    public boolean update(PaymentDetail dto) throws SQLException{
        return false;
    }

    @Override
    public PaymentDetail search(String id) throws SQLException{
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException{
        return false;
    }

    @Override
    public String generateId() throws SQLException{
        return null;
    }
}
