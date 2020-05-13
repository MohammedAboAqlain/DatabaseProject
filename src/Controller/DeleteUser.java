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

public class DeleteUser implements Initializable {


    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public Label statusLabel;
    @FXML public ComboBox comboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(FXCollections.observableArrayList(db.getVolunteers()));
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Users);
    }

    public void onConfirmBtn(ActionEvent event) {
        if(db.DeleteVolunteer(comboBox.getValue().toString()) == 1){
            statusLabel.setText("User deleted successfully...");
        }else {
            statusLabel.setText("User not deleted...");
        }
    }
}
