package Controller;

import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class LoginAs {
    private static boolean admin;
    private Navigation nav = new Navigation();

    public static boolean isAdmin() {
        return admin;
    }

    @FXML
    public AnchorPane rootPane;

    public void viewAdminLogin(ActionEvent event) {
        nav.navTo(rootPane, nav.UserLogin);
        admin = true;
    }

    public void viewVolunteerLogin(ActionEvent event) {
        nav.navTo(rootPane, nav.UserLogin);
        admin = false;
    }
}
