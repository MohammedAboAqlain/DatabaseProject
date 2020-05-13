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

public class Attendance implements Initializable {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();
    private static String councilNameAtt;
    private static String lectureNumberAtt;

    @FXML public AnchorPane rootPane;
    @FXML public ComboBox lectureNumber;
    @FXML public ComboBox councilName;
    @FXML public TextField studentName;
    @FXML public TextField familyName;
    @FXML public TextField grandParentName;
    @FXML public TextField parentName;
    @FXML public TextField studentID;
    @FXML public TextField studentMobile;
    @FXML public Label statusLabel;

    public static String getCouncilNameAtt() {
        return councilNameAtt;
    }

    public static void setCouncilNameAtt(String councilNameAtt) {
        Attendance.councilNameAtt = councilNameAtt;
    }

    public static String getLectureNumberAtt() {
        return lectureNumberAtt;
    }

    public static void setLectureNumberAtt(String lectureNumberAtt) {
        Attendance.lectureNumberAtt = lectureNumberAtt;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        councilName.setItems(FXCollections.observableArrayList(db.getCouncils()));
    }

    public void initializeFields(ActionEvent event) {
        lectureNumber.setItems(FXCollections.observableArrayList(db.getLectureNumbers(councilName.getValue().toString())));
    }

    public void onConfirmationBtn(ActionEvent event) {
        if(councilName.getValue() != null && lectureNumber.getValue() != null) {
            if (!studentName.getText().equals("") && !parentName.getText().equals("") && !grandParentName.getText().equals("") &&
                    !familyName.getText().equals("") && !studentID.getText().equals("") && !studentMobile.getText().equals("")) {

                registerAttendance();

            } else if (!studentName.getText().equals("") && !parentName.getText().equals("") && !grandParentName.getText().equals("") &&
                    !familyName.getText().equals("")) {
                studentID.setText(db.getStudentIDFromName(studentName.getText(), parentName.getText(),
                        grandParentName.getText(), familyName.getText()));
                studentMobile.setText(db.getMobileNumbers(studentID.getText()).get(0));

                registerAttendance();

            } else if (!studentID.getText().equals("")) {
                ArrayList<String> studentInfo = db.getStudentInfo(studentID.getText());
                studentName.setText(studentInfo.get(1));
                parentName.setText(studentInfo.get(2));
                grandParentName.setText(studentInfo.get(3));
                familyName.setText(studentInfo.get(4));
                studentMobile.setText(studentInfo.get(5));

                registerAttendance();

            } else if (!studentMobile.getText().equals("")) {
                studentID.setText(db.getStudentIDFromMobile(studentMobile.getText()));
                ArrayList<String> studentInfo = db.getStudentInfo(studentID.getText());
                studentName.setText(studentInfo.get(1));
                parentName.setText(studentInfo.get(2));
                grandParentName.setText(studentInfo.get(3));
                familyName.setText(studentInfo.get(4));

                registerAttendance();
            }else {
                statusLabel.setText("information is not sufficient...");
            }
        }else {
            statusLabel.setText("select council name and lecture number...");
        }
    }

    private void registerAttendance(){
        if (db.registerAttendance(councilName.getValue().toString(), lectureNumber.getValue().toString(),
                studentID.getText(), studentName.getText(), parentName.getText(),
                grandParentName.getText(), familyName.getText(), studentMobile.getText()) == 1) {
            statusLabel.setText("Student Registered Successfully...");
        } else {
            statusLabel.setText("error: Student not Registered...");
        }
    }

    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.VolunteerUI);
    }

    public void onChooseExcelFile(ActionEvent event) {
        ExcelAttendance excelAttendance = new ExcelAttendance();
        excelAttendance.setCouncilAndLectureNumber(councilName.getValue().toString(), lectureNumber.getValue().toString());
        nav.navTo(rootPane, nav.ExcelAttendance);
    }
}
