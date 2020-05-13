package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreateUser {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();
    
    @FXML public AnchorPane rootPane;
    @FXML public TextField VolunteerlName;
    @FXML public PasswordField password;
    @FXML public Label statusLabel;
    
    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Users);
    }

    public void onConfirmBtn(ActionEvent event) {
        if(db.createVolunteer(VolunteerlName.getText(), password.getText()) == 1){
            statusLabel.setText("User created successfully...");
        }else{
            statusLabel.setText("User not created...");
        }
        VolunteerlName.setText("");
        password.setText("");
        VolunteerlName.requestFocus();
    }
}
