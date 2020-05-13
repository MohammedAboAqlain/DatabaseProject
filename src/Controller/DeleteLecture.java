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

public class DeleteLecture implements Initializable {


    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML
    public AnchorPane rootPane;
    @FXML public ComboBox lectureNumber;
    @FXML public ComboBox councilName;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        councilName.setItems(FXCollections.observableArrayList(db.getCouncils()));
    }

    public void initializeFields(ActionEvent event) {
        lectureNumber.setItems(FXCollections.observableArrayList(db.getLectureNumbers(councilName.getValue().toString())));
    }

    public void onDeleteLectureBtn(ActionEvent event) {
        if(db.DeleteLecture(councilName.getValue().toString(), lectureNumber.getValue().toString()) == 1){
            statusLabel.setText("Lecture deleted successfully...");
        }else {
            statusLabel.setText("Lecture not deleted...");
        }
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Lectures);
    }

}
