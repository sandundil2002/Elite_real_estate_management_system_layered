package lk.ijse.elite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.*;
import lk.ijse.elite.bo.custom.impl.*;
import lk.ijse.elite.db.DbConnection;
import lk.ijse.elite.dto.CustomerDTO;
import lk.ijse.elite.dto.PropertyDTO;
import lk.ijse.elite.entity.Payment;
import lk.ijse.elite.entity.PaymentDetail;
import lk.ijse.elite.entity.Rent;
import lk.ijse.elite.entity.RentingDetail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class RentPropertyFormController {
    public TextField txtCustomerName;
    public TextField txtPropertyPrice;
    public DatePicker txtDate;
    public TextField txtRentid;
    public JFXComboBox comProid;
    public JFXComboBox comCusid;
    public TextField txtpayId;
    public ChoiceBox cmdPaymethod;
    public ChoiceBox cmdDuration;
    RentingBO rentingBO = (RentingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RENTING);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    PropertyBO propertyBO = (PropertyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROPERTY);


    public void initialize(){
        try {
            loadAllProperty();
            loadAllCustomer();
            autoGenarateRentId();
            autoGenaratePaymentId();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        txtDate.setValue(java.time.LocalDate.now());

        comProid.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                PropertyDTO propertyDto = propertyBO.searchProperty(t1.toString());
                txtPropertyPrice.setText(propertyDto.getPrice());
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        });

        comCusid.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                CustomerDTO customerDto = customerBO.searchCustomer(t1.toString());
                txtCustomerName.setText(customerDto.getName());
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        });

        cmdPaymethod.getItems().addAll("Cash","Card","Cheque","Online");
        cmdPaymethod.setValue("Cash");

        cmdDuration.getItems().addAll("3 Months","6 Months","1 Year","2 Years","5 Years");
        cmdDuration.setValue("3 Months");
    }

    public void btnRentOnAction(ActionEvent actionEvent) {
        String rentId = txtRentid.getText();
        String paymentId = txtpayId.getText();
        String customerId = String.valueOf(comCusid.getValue());
        String propertyId = String.valueOf(comProid.getValue());
        String name = txtCustomerName.getText();
        String price = txtPropertyPrice.getText();
        String method = String.valueOf(cmdPaymethod.getValue());
        String date = txtDate.getValue().toString();
        String duration = String.valueOf(cmdDuration.getValue());

        Rent rent = new Rent(rentId,propertyId,customerId,date,duration);
        RentingDetail rentingDetail = new RentingDetail(rentId,propertyId,duration);
        Payment payment = new Payment(paymentId,customerId,propertyId,date,price,method);
        PaymentDetail paymentDetail = new PaymentDetail(propertyId,paymentId,method);

        try {
            boolean isSuccess = rentingBO.isRentOrderSuccess(rent,rentingDetail,payment,paymentDetail);
            if (isSuccess){
                jasperReport();
                btnRentClearOnAction();
                autoGenarateRentId();
            }
        } catch(SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnRentClearOnAction() {
        txtCustomerName.clear();
        txtPropertyPrice.clear();
    }

    private void loadAllProperty() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PropertyDTO> proList = propertyBO.loadAllProperty();

            for (PropertyDTO propertyDto  : proList) {
                obList.add(propertyDto.getPropertyId());
            }
            comProid.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllCustomer() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> cusList = customerBO.loadAllCustomer();

            for (CustomerDTO customerDto  : cusList) {
                obList.add(customerDto.getCustomer_id());
            }

            comCusid.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void jasperReport(){
        HashMap buyProperty = new HashMap<>();
        buyProperty.put("Payment_id",txtpayId.getText());

        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/buyProperty.jrxml");
        try {
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            compileReport,
                            buyProperty,
                            DbConnection.getInstance().getConnection()
                    );
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void autoGenarateRentId() throws SQLException, ClassNotFoundException {
        txtRentid.setText(rentingBO.generateRentingId());
    }

    private void autoGenaratePaymentId() throws SQLException, ClassNotFoundException {
        txtpayId.setText(paymentBO.generatePaymentId());
    }
}
