package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class VolunteerAndCouncils implements Initializable {



    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public ComboBox VolunteerComboBox;
    @FXML public ComboBox CouncilComboBox;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VolunteerComboBox.setItems(FXCollections.observableArrayList(db.getVolunteers()));
        CouncilComboBox.setItems(FXCollections.observableArrayList(db.getCouncils()));
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.AdminUI);
    }

    public void onConfirmBtn(ActionEvent event) {
        if(db.VolunteerJoinCouncil(VolunteerComboBox.getValue().toString(), CouncilComboBox.getValue().toString()) == 1){
            statusLabel.setText("Volunteer Joined Successfully...");
        }else {
            statusLabel.setText("error: Volunteer not joined...");
        }
    }
}
