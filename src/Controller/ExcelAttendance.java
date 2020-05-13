package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelAttendance {

    private String studentName;
    private String parentName;
    private String grandParentName;
    private String familyName;
    private String studentID;
    private String mobileNumber;
    public static String councilName;
    public static String lectureNumber;


    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public TextField path;
    @FXML public Label statusLabel;
    
    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Attendance);
    }

    public void onImportFileBtn(ActionEvent event) throws IOException {
        FileInputStream fis = new FileInputStream(path.getText());
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        ArrayList<String> studentInfo;

        for(Row row : sheet){
            for(Cell cell : row){
                mobileNumber = cell.getStringCellValue();
                if(!mobileNumber.equals("") && mobileNumber != null) {
                    studentID = db.getStudentIDFromMobile(mobileNumber);
                    if(!studentID.equals("") && studentID != null) {
                        studentInfo = db.getStudentInfo(studentID);
                        studentName = studentInfo.get(1);
                        parentName = studentInfo.get(2);
                        grandParentName = studentInfo.get(3);
                        familyName = studentInfo.get(4);
                        registerAttendance();
                    }
                }
            }
        }
    }

    private void registerAttendance(){
        if (db.registerAttendance(councilName, lectureNumber, studentID, studentName,
                parentName, grandParentName, familyName, mobileNumber) == 1) {
            statusLabel.setText("Student Registered Successfully...");
        } else {
            statusLabel.setText("error: Student not Registered...");
        }
    }

    public void onChooseFileBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("excel file", "*.xlsx"));
        File file = fileChooser.showOpenDialog(null);
        path.setText(file.getAbsolutePath());
    }

    public void setCouncilAndLectureNumber(String councilName, String lectureNumber){
        this.councilName = councilName;
        this.mobileNumber = lectureNumber;
    }
}
