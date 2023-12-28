package lk.ijse.elite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.elite.bo.custom.PaymentBO;
import lk.ijse.elite.bo.custom.impl.PaymentBOImpl;
import lk.ijse.elite.dto.PaymentDTO;
import lk.ijse.elite.dto.tm.PaymentTM;
import java.sql.SQLException;
import java.util.List;

public class PaymentsFormController {
    public TableColumn colPaymentId;
    public TableColumn colPropertyId;
    public TableColumn colCustomerId;
    public TableColumn colDate;
    public TableColumn colPrice;
    public TableColumn colMethod;
    @FXML
    private TableView<PaymentTM> tblPayment;
    PaymentBO paymentBO = new PaymentBOImpl();

    public void initialize() {
        cellValueFactory();
        loadAllPayments();
    }

    private void cellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colPropertyId.setCellValueFactory(new PropertyValueFactory<>("property_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTM> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDTO> dtoList = paymentBO.loadAllPayment();

            for (PaymentDTO dto : dtoList) {
                obList.add(new PaymentTM(
                        dto.getPayment_id(),
                        dto.getCustomer_id(),
                        dto.getProperty_id(),
                        dto.getDate(),
                        dto.getPrice(),
                        dto.getMethod()
                ));
            }
            tblPayment.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}

