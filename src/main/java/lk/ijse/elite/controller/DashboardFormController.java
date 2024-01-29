package lk.ijse.elite.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.io.IOException;

public class DashboardFormController {

    @FXML
    private Pane bodyPane;

    @FXML
    private AnchorPane dashboard;

    public void initialize() throws IOException {
        btnDashboardOnAction();
    }

    @FXML
    private void btnPropertyOnAction(){
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/PropertyForm.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnEmployeeOnAction() {
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/EmployeeForm.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnSignoutOnAction() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/adminlogin_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) dashboard.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        Animation(anchorPane);
    }

    @FXML
    private void btnDashboardOnAction() {
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/mainDashboardForm.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnAppoinmentsOnAction() {
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/shedule_form.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnCustomersOnAction() {
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/customerForm.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnPaymentsOnAction() {
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/PaymentsForm.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnRentalOnAction() {
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/RentalForm.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnAgentsOnAction() {
        bodyPane.getChildren().clear();
        try {
            AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/AgentForm.fxml"));
            bodyPane.getChildren().add(anchorPane);
            Animation(anchorPane);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public static void Animation(AnchorPane anchorPane){
        TranslateTransition transition = new TranslateTransition(Duration.millis(350), anchorPane);
        transition.setFromX(-anchorPane.getPrefWidth());
        transition.setToX(0);
        transition.play();
    }
}
