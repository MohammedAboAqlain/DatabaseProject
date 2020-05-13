package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class UserLogin {
    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public TextField userName;
    @FXML public PasswordField password;
    @FXML public Label statusLabel;

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.loginAs);
    }

    public void login(ActionEvent event) {
        if(userName.getText() != null && password.getText() != null){
            if(LoginAs.isAdmin()) {
                if(userName.getText().equals("admin") && password.getText().equals("admin")){
                    DBModel.setCurrentUser(userName.getText());
                    DBModel.setPassword(password.getText());
                    db.schemaConnect("public");
                    nav.navTo(rootPane, nav.AdminUI);
                }else {
                    statusLabel.setText("Username or Password is incorrect...");
                    userName.setText("");
                    password.setText("");
                    userName.requestFocus();
                }
            }else{
                if (db.checkVolunteer(userName.getText(), password.getText())) {
                    DBModel.setCurrentUser(userName.getText());
                    DBModel.setPassword(password.getText());
                    db.schemaConnect("public");
                    nav.navTo(rootPane, nav.VolunteerUI);
                }else {
                    statusLabel.setText("Username or Password is incorrect...");
                    userName.setText("");
                    password.setText("");
                    userName.requestFocus();
                }
            }
        }
    }
}
