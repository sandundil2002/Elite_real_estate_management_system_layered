package lk.ijse.elite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.elite.bo.custom.DashboardBO;
import lk.ijse.elite.bo.custom.impl.DashboardBOImpl;
import lk.ijse.elite.dto.TodayAppoinmentsDTO;
import lk.ijse.elite.dto.tm.TodayAppointmentsTM;
import lk.ijse.elite.util.SQLUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainDashboardFormController {
    public Label lblTime;
    public Label lblDate;
    public TableColumn colSheduleid;
    public TableColumn colCusname;
    public TableColumn colTime;
    public TableColumn colMobile;
    public TableView tblTodayAppointment;
    public Label lblTotalPro;
    public Label lblTotalApp;
    public BarChart<String, Number> barChart;
    DashboardBO dashboardBO = new DashboardBOImpl();

    public void initialize() {
        loadTodayShedules();
        setCellValueFactory();
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EEEE(dd)-MMM-yyyy");
        String formattedTime = time.format(formatter);
        String formattedDate = date.format(formatter1);

        lblTime.setText(formattedTime);
        lblDate.setText(formattedDate);
        lblTotalApp.setText(String.valueOf(getTotalAppointmentsCount()));
        lblTotalPro.setText(String.valueOf(getTotalPropertiesCount()));

        try {
            populateChart(barChart);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void populateChart(BarChart<String, Number> barChart) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.sql("SELECT Type, COUNT(*) as Count FROM Property GROUP BY Type");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        while (resultSet.next()) {
            String propertyType = resultSet.getString("Type");
            int count = resultSet.getInt("Count");
            series.getData().add(new XYChart.Data<>(propertyType, count));
        }
        barChart.getData().add(series);
        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #E0A383;");
        }
    }

    private int getTotalPropertiesCount() {
        try {
            return dashboardBO.getTotalPropertiesCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0;
    }

    private int getTotalAppointmentsCount() {
        try {
            return dashboardBO.getTotalAppointmentsCount();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        return 0;
    }

    private void setCellValueFactory(){
        colSheduleid.setCellValueFactory(new PropertyValueFactory<>("shedule_id"));
        colCusname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    }

    private void loadTodayShedules(){
        ObservableList<TodayAppointmentsTM> obList = FXCollections.observableArrayList();

        try{
            List<TodayAppoinmentsDTO> dtoList = dashboardBO.loadTodayShedules();

            for (TodayAppoinmentsDTO dto : dtoList) {
                obList.add(new TodayAppointmentsTM(
                        dto.getShedule_id(),
                        dto.getName(),
                        dto.getTime(),
                        dto.getMobile()
                ));
            }
            tblTodayAppointment.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
