package lk.ijse.elite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.elite.bo.custom.MaintainBO;
import lk.ijse.elite.bo.custom.RentingBO;
import lk.ijse.elite.bo.custom.impl.MaintainBOImpl;
import lk.ijse.elite.bo.custom.impl.RentingBOImpl;
import lk.ijse.elite.dto.MaintainDTO;
import lk.ijse.elite.dto.RentingDTO;
import java.sql.SQLException;
import java.util.List;

public class MaintainmanageFormController {
    public DatePicker dtpDate;
    public TextField txtMaintainId;
    public TextField txtStatus;
    public JFXComboBox cmbRentId;
    MaintainBO maintainBO = new MaintainBOImpl();
    RentingBO rentingBO = new RentingBOImpl();

    public void initialize(){
        try {
            autoGenerateId();
            loadAllRentIds();
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        dtpDate.setValue(java.time.LocalDate.now());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String maintainId = txtMaintainId.getText();
        String rentId = String.valueOf(cmbRentId.getValue());
        String date = String.valueOf(dtpDate.getValue());
        String status = txtStatus.getText();

        var dto = new MaintainDTO(maintainId, rentId, date, status);

        try {
            boolean isAdded = maintainBO.saveMaintain(dto);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Maintenance Added Successfully").show();
                initialize();
            } else {
                new Alert(Alert.AlertType.WARNING, "Maintenance Not Added Please try again!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Maintenance Not Added Please try again!!!").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING, "Maintenance Not Added Please try again!!!").show();
        }
    }

    private void loadAllRentIds(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<RentingDTO> rentList = rentingBO.loadAllRenting();

            for (RentingDTO rentingDto  : rentList) {
                obList.add(rentingDto.getRent_id());
            }

            cmbRentId.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
        }
    }

    private void autoGenerateId() throws SQLException, ClassNotFoundException {
        txtMaintainId.setText(maintainBO.generateMaintainId());
    }
}
