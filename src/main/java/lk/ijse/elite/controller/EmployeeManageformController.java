package lk.ijse.elite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.AdminBO;
import lk.ijse.elite.bo.custom.EmployeeBO;
import lk.ijse.elite.dto.AdminDTO;
import lk.ijse.elite.dto.EmployeeDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeManageformController {

    @FXML
    private TextField txtEmpid;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtMobile;

    @FXML
    private JFXComboBox cmbAdminid;

    @FXML
    private JFXComboBox cmbEmployeeposition;

    @FXML
    private TextField txtAmount;

    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);

    public final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    public void initialize(){
        try {
            autoGenerateId();
            loadAllAdmin();
        } catch (SQLException  | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        cmbEmployeeposition.getItems().addAll("Director","Project Manager","Chief Operation","Chief Executive","lawyer","General Manager","Sales Manager","Charted Accountant","Admin Manager","Customer Service");

        cmbEmployeeposition.setOnAction(event -> {
            String selectedPosition = String.valueOf(cmbEmployeeposition.getValue());
            String amount = getAmountByPosition(selectedPosition);
            txtAmount.setText(amount);
        });
    }

    private String getAmountByPosition(String selectedPosition) {
        switch (selectedPosition) {
            case "Director":
                return "$14000";
            case "Project Manager":
                return "$11000";
            case "Chief Operation":
                return "$10000";
            case "Chief Executive":
                return "$9000";
            case "lawyer":
                return "$8000";
            case "General Manager":
                return "$6000";
            case "Sales Manager":
                return "$5000";
            case "Charted Accountant":
                return "$5000";
            case "Admin Manager":
                return "$4000";
            case "Customer Service":
                return "$3000";
            default:
                return "0";
        }
    }

    @FXML
    private void btnSaveOnAction() {
        String eid = txtEmpid.getText();
        String adid = String.valueOf(cmbAdminid.getValue());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String mobile = txtMobile.getText();
        String position = String.valueOf(cmbEmployeeposition.getValue());
        String sal = txtAmount.getText();

        boolean isEmployeeValidated = validateEmployee();
        if (!isEmployeeValidated) {
            return;
        }

        var dto = new EmployeeDTO(eid,adid, name, address, mobile, position, sal);

        try {
            boolean isSaved = employeeBO.saveEmployee(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Saved Succesfull").show();
                initialize();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtEmpid.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtMobile.setText("");
    }

    @FXML
    private void btnUpdateOnAction() {
        String eid = txtEmpid.getText();
        String adid = String.valueOf(cmbAdminid.getValue());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String mobile = txtMobile.getText();
        String position = String.valueOf(cmbEmployeeposition.getValue());
        String sal = txtAmount.getText();

        boolean isEmployeeValidated = validateEmployee();
        if (!isEmployeeValidated) {
            return;
        }

        var dto = new EmployeeDTO(eid,adid, name, address, mobile, position, sal);

        try {
            boolean isUpdated = employeeBO.updateEmployee(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Update Succesfull!!!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    public void btnDeleteOnAction() {
        String eid = txtEmpid.getText();

        try{
            EmployeeDTO dto = employeeBO.searchEmployee(eid);
            if(dto != null) {
                boolean isDeleted = employeeBO.deleteEmployee(eid);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Delete Succesfull!!!").show();
                    initialize();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Employee Not Found!!!").show();
                initialize();
            }
        } catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnSearchOnAction() {
        String eid = txtEmpid.getText();

        try {
            EmployeeDTO dto = employeeBO.searchEmployee(eid);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
                initialize();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(EmployeeDTO dto) {
        txtEmpid.setText(dto.getEmpid());
        cmbAdminid.setValue(dto.getAdid());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtMobile.setText(dto.getMobile());
        cmbEmployeeposition.setValue(dto.getPosition());
    }

    private void loadAllAdmin() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<AdminDTO> adList = adminBO.loadAllAdmin();

            for (AdminDTO adminDto  : adList) {
                obList.add(adminDto.getAdmin_id());
            }
            cmbAdminid.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateEmployee(){
        String name = txtName.getText();
        boolean isNameValid = Pattern.compile("^[A-z ]{3,}$").matcher(name).matches();
        if (!isNameValid){
            txtName.requestFocus();
            txtName.setStyle("-fx-border-color: red");
            return false;
        }

        String address = txtAddress.getText();
        boolean isAddressValid = Pattern.compile("^[A-z0-9 ,]{3,}$").matcher(address).matches();
        if (!isAddressValid){
            txtAddress.requestFocus();
            txtAddress.setStyle("-fx-border-color: red");
            return false;
        }

        String mobile = txtMobile.getText();
        boolean isMobileValid = Pattern.compile("^[0-9]{10}$").matcher(mobile).matches();
        if (!isMobileValid){
            txtMobile.requestFocus();
            txtMobile.setStyle("-fx-border-color: red");
            return false;
        }
        return true;
    }

    private void autoGenerateId() throws SQLException, ClassNotFoundException {
        txtEmpid.setText(employeeBO.generateEmployeeId());
    }
}
