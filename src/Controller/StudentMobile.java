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
import java.util.ResourceBundle;

public class StudentMobile implements Initializable {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public ComboBox StudentNumbersComboBox;
    @FXML public ComboBox DeleteNumberComboBox;
    @FXML public TextField studentMobile;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentNumbersComboBox.setItems(FXCollections.observableArrayList(db.getStudents()));
    }

    public void onStudentNumbersComboBox(ActionEvent event) {
        DeleteNumberComboBox.setItems(FXCollections.observableArrayList(db.getMobileNumbers(StudentNumbersComboBox.getValue().toString())));
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.UpdateStudent);
    }

    public void onAddBtn(ActionEvent event) {
        if(studentMobile.getText() != null){
            if(db.addMobile(StudentNumbersComboBox.getValue().toString(), studentMobile.getText()) == 1){
                statusLabel.setText("Mobile Added Successfully...");
            }else{
                statusLabel.setText("error: Mobile not Added...");
            }
        }
    }

    public void onDeleteBtn(ActionEvent event) {
        if(DeleteNumberComboBox.getValue() != null){
            if(db.deleteMobile(StudentNumbersComboBox.getValue().toString(), DeleteNumberComboBox.getValue().toString()) == 1){
                statusLabel.setText("Mobile deleted Successfully...");
            }else{
                statusLabel.setText("error: Mobile not deleted...");
            }
        }
    }
}
