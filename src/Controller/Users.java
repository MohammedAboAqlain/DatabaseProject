package Controller;

import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Users {

    Navigation nav = new Navigation();

    @FXML
    public AnchorPane rootPane;

    public void onCreateUser(ActionEvent event) {
        nav.navTo(rootPane, nav.CreateUser);
    }

    public void onDeleteUser(ActionEvent event) {
        nav.navTo(rootPane, nav.DeleteUser);
    }

    public void onUpdateUser(ActionEvent event) {
        nav.navTo(rootPane, nav.UpdateUser);
    }

    public void onBackbtn(ActionEvent event) {
        nav.navTo(rootPane, nav.AdminUI);
    }
}
