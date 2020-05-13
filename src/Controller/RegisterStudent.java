package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterStudent {


    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public TextField studentName;
    @FXML public TextField familyName;
    @FXML public TextField grandParentName;
    @FXML public TextField parentName;
    @FXML public TextField place;
    @FXML public TextField studentID;
    @FXML public TextField studentMobile;
    @FXML public Label statusLabel;

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Student);
    }

    public void RegisterStudent(ActionEvent event) {
        /*if(db.checkStudentID(studentID.getText()) &&
                db.checkStudentName(studentName.getText(), parentName.getText(),
                        grandParentName.getText(), familyName.getText())){*/
        if(db.RegisterStudent(studentName.getText(), parentName.getText(), grandParentName.getText(),
                familyName.getText(), studentID.getText(), place.getText(), studentMobile.getText()) == 1){
            statusLabel.setText("Student Registered Successfully...");
        }else {
            statusLabel.setText("error: Student not registered");
        }
        /*}else {
            statusLabel.setText("error: Student ID or Name already exists...");
        }*/
        studentName.setText("");
        studentID.setText("");
        studentMobile.setText("");
        parentName.setText("");
        grandParentName.setText("");
        familyName.setText("");
        place.setText("");
        studentName.requestFocus();
    }
}
