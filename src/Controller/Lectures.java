package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Lectures {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;


    public void onBackbtn(ActionEvent event) {
        nav.navTo(rootPane, nav.VolunteerUI);
    }

    public void onAddLecture(ActionEvent event) {
        nav.navTo(rootPane, nav.AddLecture);
    }

    public void onDeleteLecture(ActionEvent event) {
        nav.navTo(rootPane, nav.DeleteLecture);
    }

    public void onUpdateLecture(ActionEvent event) {
        nav.navTo(rootPane, nav.UpdateLecture);
    }
}
