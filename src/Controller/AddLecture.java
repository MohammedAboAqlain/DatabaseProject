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

public class AddLecture implements Initializable {



    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public TextField lectureNumber;
    @FXML public TextField subject;
    @FXML public TextField shaikhName;
    @FXML public ComboBox councilName;
    @FXML public TextField place;
    @FXML public Label statusLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        councilName.setItems(FXCollections.observableArrayList(db.getCouncils()));
    }


    public void onAddLectureBtn(ActionEvent event) {
        if(db.addLecture(lectureNumber.getText(), councilName.getValue().toString(), shaikhName.getText(),
                subject.getText(),  place.getText()) == 1){
            statusLabel.setText("Lecture Added Successfully...");
        }else{
            statusLabel.setText("error: Lecture not Added...");
        }
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Lectures);
    }

    public void initializeFields(ActionEvent event) {
        shaikhName.setText(db.getShaikhName(councilName.getValue().toString()));
        place.setText(db.getPlace(shaikhName.getText()));
        shaikhName.setEditable(false);
    }
}


