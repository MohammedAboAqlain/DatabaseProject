package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Student {
    
    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();
    
    @FXML public AnchorPane rootPane;
    
    public void onBackbtn(ActionEvent event) {
        nav.navTo(rootPane, nav.VolunteerUI);
    }

    public void onRegisterStudent(ActionEvent event) {
        nav.navTo(rootPane, nav.RegisterStudent);
    }

    public void onDeleteStudent(ActionEvent event) {
        nav.navTo(rootPane, nav.DeleteStudent);
    }

    public void onUpdateStudent(ActionEvent event) {
        nav.navTo(rootPane, nav.UpdateStudent);
    }
}
