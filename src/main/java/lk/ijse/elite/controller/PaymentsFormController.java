package lk.ijse.elite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.PaymentBO;
import lk.ijse.elite.dto.PaymentDTO;
import lk.ijse.elite.dto.tm.PaymentTM;
import java.sql.SQLException;
import java.util.List;

public class PaymentsFormController {

    @FXML
    private TableColumn colPaymentId;

    @FXML
    private TableColumn colPropertyId;

    @FXML
    private TableColumn colCustomerId;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colMethod;

    @FXML
    private TableView<PaymentTM> tblPayment;

    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);

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
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}

