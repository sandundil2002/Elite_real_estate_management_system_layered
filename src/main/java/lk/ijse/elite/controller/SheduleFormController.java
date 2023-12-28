package lk.ijse.elite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.elite.bo.custom.AdminBO;
import lk.ijse.elite.bo.custom.ScheduleBO;
import lk.ijse.elite.bo.custom.impl.AdminBOImpl;
import lk.ijse.elite.bo.custom.impl.ScheduleBOImpl;
import lk.ijse.elite.dto.AdminDTO;
import lk.ijse.elite.dto.ScheduleDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SheduleFormController {
    public Pane bodyPane;
    public TextField txtSheduleId;
    public TextField txtTime;
    public DatePicker txtDate;
    public ComboBox cmbAdminId;
    public TextField txtStatus;
    ScheduleBO scheduleBO = new ScheduleBOImpl();
    AdminBO adminBO = new AdminBOImpl();

    public void initialize(){
        try {
            autoGenerateId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllAdmins();

        cmbAdminId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                adminBO.searchAdmin(t1.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
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
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
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
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String shedule_id = txtSheduleId.getText();

        try{
            ScheduleDTO dto = scheduleBO.searchSchedule(shedule_id);
            if(dto != null) {
                boolean isDeleted = scheduleBO.deleteSchedule(shedule_id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Schedule Delete Succesfull!!!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Schedule Not Found!!!").show();
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnbackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml/"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) bodyPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    private void loadAllAdmins() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<AdminDTO> adminList = adminBO.loadAllAdmin();

            for (AdminDTO adminDto  : adminList) {
                obList.add(adminDto.getAdmin_id());
            }

            cmbAdminId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public void autoGenerateId() throws SQLException, ClassNotFoundException {
        txtSheduleId.setText(scheduleBO.generateScheduleId());
    }
}
