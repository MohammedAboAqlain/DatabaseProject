package Controller;

import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AdminUI {

    Navigation nav = new Navigation();

    @FXML public AnchorPane rootPane;
    @FXML public Button council;

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.loginAs);
    }


    public void onCouncilBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Council);
    }

    public void onUsersBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Users);
    }

    public void onVolAndCouncilsBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.VolunteerAndCouncils);
    }
}
