package lk.ijse.elite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.*;
import lk.ijse.elite.db.DbConnection;
import lk.ijse.elite.entity.*;
import lk.ijse.elite.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PlaceorderFromController {

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtPropertyPrice;

    @FXML
    private ComboBox comProid;

    @FXML
    private ComboBox comCusid;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtPaymentid;

    @FXML
    private ChoiceBox cmdPaymethod;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

    private final PropertyBO propertyBO = (PropertyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROPERTY);

    public void initialize(){
        try {
            autoGenarateId();
            loadAllProperty();
            loadAllCustomer();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
    }

    @FXML
    private void btnBuyOnAction() {
        String paymentId = txtPaymentid.getText();
        String customerId = String.valueOf(comCusid.getValue());
        String propertyId = String.valueOf(comProid.getValue());
        String price = txtPropertyPrice.getText();
        String method = String.valueOf(cmdPaymethod.getValue());
        String date = txtDate.getValue().toString();


        Payment payment = new Payment(paymentId,customerId,propertyId,date,price,method);
        PaymentDetail paymentDetail = new PaymentDetail(propertyId,paymentId,method);

        try {
            boolean isSuccess = paymentBO.isSellOrderSuccess(payment,paymentDetail);
            if (isSuccess) {
                jasperReport();
                btnClearOnAction();
                autoGenarateId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnClearOnAction() {
        txtPaymentid.clear();
        txtCustomerName.clear();
        txtPropertyPrice.clear();
        txtDate.getEditor().clear();
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
        buyProperty.put("Payment_id",txtPaymentid.getText());

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
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void autoGenarateId() throws SQLException, ClassNotFoundException {
        txtPaymentid.setText(paymentBO.generatePaymentId());
    }
}
