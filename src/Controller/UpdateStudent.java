package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateStudent implements Initializable {


    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public ComboBox comboBox;
    @FXML public TextField studentName;
    @FXML public TextField parentName;
    @FXML public TextField grandParentName;
    @FXML public TextField familyName;
    @FXML public TextField studentID;
    @FXML public TextField place;
    @FXML public TextField studentMobile;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(FXCollections.observableArrayList(db.getStudents()));
    }

    public void onComboBoxAction(ActionEvent event) {
        ArrayList<String> fields = db.getStudentInfo(comboBox.getValue().toString());
        place.setText(fields.get(0));
        studentName.setText(fields.get(1));
        parentName.setText(fields.get(2));
        grandParentName.setText(fields.get(3));
        familyName.setText(fields.get(4));
        studentMobile.setText(fields.get(5));
        studentID.setText(comboBox.getValue().toString());
        studentID.setEditable(false);
    }

    public void UpdateStudent(ActionEvent event) {
        if(db.UpdateStudentInfo(comboBox.getValue().toString(), studentName.getText(), parentName.getText(),
                grandParentName.getText(), familyName.getText(), studentMobile.getText(), place.getText()) == 1){
            statusLabel.setText("Student Updated Successfully...");
        }else{
            statusLabel.setText("error: There is no update occur...");
        }
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Student);
    }

    public void onAddOrDeleteMobileBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.StudentMobile);
    }
}
