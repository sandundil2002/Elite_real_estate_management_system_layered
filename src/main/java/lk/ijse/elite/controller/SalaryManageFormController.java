package lk.ijse.elite.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.elite.bo.custom.EmployeeBO;
import lk.ijse.elite.bo.custom.SalaryBO;
import lk.ijse.elite.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.elite.bo.custom.impl.SalaryBOImpl;
import lk.ijse.elite.entity.Employee;
import lk.ijse.elite.dto.EmployeeDTO;
import lk.ijse.elite.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.List;

public class SalaryManageFormController {
    public TextField txtSalaryid;
    public TextField txtEmployeeid;
    public TextField txtName;
    public JFXComboBox cmdPosition;
    public DatePicker dtpDate;
    public TextField txtAmount;
    SalaryBO salaryBO = new SalaryBOImpl();
    EmployeeBO employeeBO = new EmployeeBOImpl();

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

    public void btnPayOnAction(ActionEvent actionEvent) {
        String salaryid = txtSalaryid.getText();
        String employeeid = txtEmployeeid.getText();
        String name = txtName.getText();
        String amount = txtAmount.getText();
        String position = cmdPosition.getValue().toString();
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

    public void btnClearOnAction() {
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
