package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class VolunteerUI {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;


    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.loginAs);
    }

    public void onRegisterStuBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Student);
    }

    public void onLecturesBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Lectures);
    }

    public void onAttendanceBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Attendance);
    }

    public void onReportsBtn(ActionEvent event) {
    }
}
