package Controller;

import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Council {
    Navigation nav = new Navigation();

    @FXML public AnchorPane rootPane;


    public void onBackbtn(ActionEvent event) {
        nav.navTo(rootPane, nav.AdminUI);
    }

    public void onCreateCouncil(ActionEvent event) {
        nav.navTo(rootPane, nav.CreateCouncil);
    }

    public void onDeleteCouncil(ActionEvent event) {
        nav.navTo(rootPane, nav.DeleteCouncil);
    }

    public void onUpdateCouncil(ActionEvent event) {
        nav.navTo(rootPane, nav.UpdateCouncil);
    }
}
