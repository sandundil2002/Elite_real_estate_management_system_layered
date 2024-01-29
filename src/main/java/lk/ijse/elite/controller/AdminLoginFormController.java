package lk.ijse.elite.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.AdminBO;

import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginFormController {

    @FXML
    private TextField txtPassword;

    @FXML
    private AnchorPane adminPane;

    @FXML
    private TextField txtAdminid;

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    @FXML
    private void btnLoginOnAction() throws IOException {
        String adminid = txtAdminid.getText();
        String password = txtPassword.getText();

        try {
            boolean isvalidAdminUserId = adminBO.searchAdminUserId(adminid);
            boolean isvalidAdminPassword = adminBO.searchAdminPassword(password);

        if(!isvalidAdminUserId) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Id Please Try Again!!!").show();
            txtAdminid.setStyle("-fx-border-color:#ff0000;");
        } else if (!isvalidAdminPassword) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password Please Try Again!!!").show();
            txtPassword.setStyle("-fx-border-color:#ff0000;");
        } else {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) adminPane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard Form");
            stage.centerOnScreen();
            DashboardFormController.Animation(anchorPane);
        }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnSignupOnAction() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/adminregister_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) adminPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Register Form");
        stage.centerOnScreen();
        DashboardFormController.Animation(anchorPane);
    }
}

