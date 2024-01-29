package lk.ijse.elite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.AdminBO;
import lk.ijse.elite.bo.custom.ScheduleBO;
import lk.ijse.elite.dto.AdminDTO;
import lk.ijse.elite.dto.ScheduleDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SheduleFormController {

    @FXML
    private TextField txtSheduleId;

    @FXML
    private TextField txtTime;

    @FXML
    private DatePicker txtDate;

    @FXML
    private ComboBox cmbAdminId;

    @FXML
    private TextField txtStatus;

    private final ScheduleBO scheduleBO = (ScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SCHEDULE);

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    public void initialize(){
        try {
            autoGenerateId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllAdmins();

        cmbAdminId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                if (adminBO == null) {
                    new Alert(Alert.AlertType.ERROR, "AdminBO is not initialized").show();
                } else {
                    adminBO.searchAdmin(t1.toString()).getAdmin_id();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        });
    }

    @FXML
    private void btnAddOnAction() {
        String shedule_id = txtSheduleId.getText();
        String admin_id = String.valueOf(cmbAdminId.getValue());
        String date = txtDate.getValue().toString();
        String time = txtTime.getText();
        String status = txtStatus.getText();

        boolean isSheduleValidated = validateShedule();
        if (!isSheduleValidated) {
            return;
        }

        var dto = new ScheduleDTO(shedule_id, admin_id, date, time, status);

        try {
            boolean isSaved = scheduleBO.saveSchedule(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Schedule Added Succesfull").show();
                initialize();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnUpdateOnAction() {
        String shedule_id = txtSheduleId.getText();
        String admin_id = String.valueOf(cmbAdminId.getValue());
        String date = txtDate.getValue().toString();
        String time = txtTime.getText();
        String status = txtStatus.getText();

        boolean isSheduleValidated = validateShedule();
        if (!isSheduleValidated) {
            return;
        }

        var dto = new ScheduleDTO(shedule_id, admin_id, date, time, status);

        try {
            boolean isUpdated = scheduleBO.updateSchedule(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Schedule Update Succesfull!!!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllAdmins() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<AdminDTO> adminList = adminBO.loadAllAdmin();

            for (AdminDTO adminDto  : adminList) {
                obList.add(adminDto.getAdmin_id());
            }
            cmbAdminId.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private boolean validateShedule() {
        String timeText = txtTime.getText();
        boolean timeValidation = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$").matcher(timeText).matches();
        if (!timeValidation) {
            new Alert(Alert.AlertType.ERROR, "Invalid Time").show();
            txtTime.requestFocus();
            txtTime.setStyle("-fx-border-color:#ff0000;");
            return false;
        }
        return true;
    }

    @FXML
    private void autoGenerateId() throws SQLException, ClassNotFoundException {
        txtSheduleId.setText(scheduleBO.generateScheduleId());
    }
}
