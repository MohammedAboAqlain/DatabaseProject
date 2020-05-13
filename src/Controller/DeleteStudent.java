package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteStudent implements Initializable {


    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public ComboBox StudentNumbersComboBox;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentNumbersComboBox.setItems(FXCollections.observableArrayList(db.getStudents()));
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Student);
    }

    public void onDeleteBtn(ActionEvent event) {
        if(StudentNumbersComboBox.getValue() != null){
            if(db.deleteStudent(StudentNumbersComboBox.getValue().toString()) == 1){
                statusLabel.setText("Student Deleted Successfully...");
            }else {
                statusLabel.setText("error: Student not deleted...");
            }
        }
    }
}
