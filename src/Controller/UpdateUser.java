package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateUser implements Initializable {


    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();
    private String old_name;


    @FXML public AnchorPane rootPane;
    @FXML public ComboBox comboBox;
    @FXML public TextField VolunteerlName;
    @FXML public PasswordField password;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(FXCollections.observableArrayList(db.getVolunteers()));
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Users);
    }

    public void onConfirmBtn(ActionEvent event) {
        if(db.UpdateVolunteer(old_name, VolunteerlName.getText(), password.getText()) == 1){
            statusLabel.setText("User Updated Successfully...");
        }else {
            statusLabel.setText("There is no update occur...");
        }
        VolunteerlName.setText("");
        password.setText("");
        VolunteerlName.requestFocus();
    }

    public void onComboBoxAction(ActionEvent event) {
        VolunteerlName.setText(comboBox.getValue().toString());
        password.setText(db.getVolunteerPassword(comboBox.getValue().toString()));
        old_name = VolunteerlName.getText();
    }
}
