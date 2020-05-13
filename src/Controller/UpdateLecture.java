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

public class UpdateLecture implements Initializable {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML
    public AnchorPane rootPane;
    @FXML public ComboBox lectureNumber;
    @FXML public TextField subject;
    @FXML public TextField shaikhName;
    @FXML public ComboBox councilName;
    @FXML public TextField place;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        councilName.setItems(FXCollections.observableArrayList(db.getCouncils()));
    }


    public void initializeFields(ActionEvent event) {
        shaikhName.setText(db.getShaikhName(councilName.getValue().toString()));
        place.setText(db.getPlace(shaikhName.getText()));
        lectureNumber.setItems(FXCollections.observableArrayList(db.getLectureNumbers(councilName.getValue().toString())));
        shaikhName.setEditable(false);
    }

    public void onUpdateLectureBtn(ActionEvent event) {
        if(db.UpdateLecture(lectureNumber.getValue().toString(), councilName.getValue().toString(),
                subject.getText(), place.getText()) == 1){
            statusLabel.setText("Lecture Updated Successfully...");
        }else {
            statusLabel.setText("Lecture not Updated...");
        }
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Lectures);
    }

    public void getSubject(ActionEvent event) {
        subject.setText(db.getSubject(councilName.getValue().toString(), lectureNumber.getValue().toString()));
    }
}
