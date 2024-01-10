package lk.ijse.elite.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.elite.bo.custom.AdminBO;
import lk.ijse.elite.bo.custom.impl.AdminBOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class AdminloginFormController {
    public TextField txtPassword;
    public AnchorPane adminPane;
    public TextField txtAdminid;
    AdminBO adminBO = new AdminBOImpl();
    DashboardFormController dashboardBO = new DashboardFormController();

    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, IOException {
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
            dashboardBO.Animation(anchorPane);
        }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSignupOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/adminregister_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) adminPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admin Register Form");
        stage.centerOnScreen();
        dashboardBO.Animation(anchorPane);
    }
}

