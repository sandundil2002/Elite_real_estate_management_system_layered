package lk.ijse.elite.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.elite.bo.BOFactory;
import lk.ijse.elite.bo.custom.AgentBO;
import lk.ijse.elite.bo.custom.PropertyBO;
import lk.ijse.elite.dto.AgentDTO;
import lk.ijse.elite.dto.PropertyDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class PropertymanageFormcCntroller {

    @FXML
    private ComboBox txtAgentid;

    @FXML
    private TextField txtStatus;

    @FXML
    private ChoiceBox cmbType;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtPerches;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtPropertyId;

    private final PropertyBO propertyBO = (PropertyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROPERTY);
    public final AgentBO agentBO = (AgentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.AGENT);

    public void initialize(){
        try {
            autoGenerateId();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllAdmin();

        txtAgentid.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                txtAgentid.setValue(agentBO.searchAgent(t1.toString()).getAgent_id());
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        });

        cmbType.getItems().addAll("Land","House","Building","Apartment","Office","Warehouse","Shop","Cabin","Farm House");
        cmbType.setValue("Land");
    }

    private void clearFields() {
        txtPropertyId.setText("");
        txtPrice.setText("");
        txtAddress.setText("");
        txtPerches.setText("");
    }

    @FXML
    private void btnUpdateOnAction() {
        String pid = txtPropertyId.getText();
        String aid = txtAgentid.toString();
        String price = txtPrice.getText();
        String address = txtAddress.getText();
        String type = String.valueOf(cmbType.getValue());
        String perches = txtPerches.getText();
        String status = txtStatus.getText();

        boolean isPropertyValidated = validateProperty();
        if (!isPropertyValidated) {
            return;
        }

        var dto = new PropertyDTO(pid, aid, price, address, type, perches, status);

        try {
            boolean isUpdated = propertyBO.updateProperty(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Property Update Succesfull!!!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void btnSearchOnAction() {
        String pid = txtPropertyId.getText();
        try {
            PropertyDTO dto = propertyBO.searchProperty(pid);
            if (dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Property Not Found!!!").show();
                initialize();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(PropertyDTO dto) {
        txtPropertyId.setText(dto.getPropertyId());
        txtPrice.setText(dto.getPrice());
        txtAddress.setText(dto.getAddress());
        cmbType.setValue(dto.getType());
        txtPerches.setText(dto.getPerches());
    }

    @FXML
    private void btnSaveOnAction() {
        String pId = txtPropertyId.getText();
        String aId = String.valueOf(txtAgentid.getValue());
        String price = txtPrice.getText();
        String address = txtAddress.getText();
        String type = String.valueOf(cmbType.getValue());
        String perches = txtPerches.getText();
        String status = txtStatus.getText();

        boolean isPropertyValidated = validateProperty();
        if (!isPropertyValidated) {
            return;
        }

        var dto = new PropertyDTO(pId, aId, price, address, type, perches, status);

        try {
            boolean isSaved = propertyBO.saveProperty(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Property Added Succesfull").show();
                clearFields();
                autoGenerateId();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllAdmin(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<AgentDTO> ageList = agentBO.loadAllAgent();

            for (AgentDTO agentDto  : ageList) {
                obList.add(agentDto.getAgent_id());
            }
            txtAgentid.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean validateProperty() {
        String priceText = txtPrice.getText();
        boolean priceValidate = Pattern.compile("[$][0-9]{3,}").matcher(priceText).matches();
        if (!priceValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Price").show();
            txtPrice.requestFocus();
            return false;
        }

        String addressText = txtAddress.getText();
        boolean addressValidate = Pattern.compile("[A-Z]{1}[a-z]{1,}").matcher(addressText).matches();
        if (!addressValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address").show();
            txtAddress.requestFocus();
            return false;
        }
        return true;
    }

    private void autoGenerateId() throws SQLException, ClassNotFoundException {
        txtPropertyId.setText(propertyBO.generatePropertyId());
    }
}
