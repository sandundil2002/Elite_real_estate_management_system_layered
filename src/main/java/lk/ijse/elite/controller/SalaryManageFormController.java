package lk.ijse.elite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.EmployeeBO;
import lk.ijse.elite.bo.custom.SalaryBO;
import lk.ijse.elite.entity.Employee;
import lk.ijse.elite.dto.EmployeeDTO;
import lk.ijse.elite.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public class SalaryManageFormController {

    @FXML
    private TextField txtSalaryid;

    @FXML
    private TextField txtEmployeeid;

    @FXML
    private TextField txtName;

    @FXML
    private JFXComboBox cmdPosition;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private TextField txtAmount;

    private final SalaryBO salaryBO = (SalaryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SALARY);

    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);


    public void initialize(){
        try {
            autoGenarateId();
        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllEmployees();
        dtpDate.setValue(java.time.LocalDate.now());

        cmdPosition.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                Employee employee = employeeBO.searchEmployeePosition(t1.toString());
                txtEmployeeid.setText(employee.getEmpid());
                txtName.setText(employee.getName());
                txtAmount.setText(employee.getBasicSalary());
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        });
        
    }

    @FXML
    private void btnPayOnAction() {
        String salaryid = txtSalaryid.getText();
        String employeeid = txtEmployeeid.getText();
        String amount = txtAmount.getText();
        String date = dtpDate.getValue().toString();

        var dto = new SalaryDTO(salaryid, employeeid, date, amount);

        try {
            boolean isSaved = salaryBO.saveSalary(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Paid Succesfull").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadAllEmployees() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDTO> empList = employeeBO.loadAllEmployee();

            for (EmployeeDTO employeeDto  : empList) {
                obList.add(employeeDto.getPosition());
            }
            cmdPosition.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnClearOnAction() {
        txtSalaryid.clear();
        txtEmployeeid.clear();
        txtName.clear();
        txtAmount.clear();
        cmdPosition.getSelectionModel().clearSelection();
        dtpDate.setValue(java.time.LocalDate.now());
    }

    private void autoGenarateId() throws SQLException, ClassNotFoundException {
        txtSalaryid.setText(salaryBO.generateSalaryId());
    }
}
