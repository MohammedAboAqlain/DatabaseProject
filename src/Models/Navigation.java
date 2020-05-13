package Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Navigation {
    private final double width = 450;
    private final double height = 450;
    // nav_add_section,nav_lectures_times,nav_enroll_student
    public final String loginAs = "/View/LoginAs.fxml";
    public final String UserLogin = "/View/UserLogin.fxml";
    public final String VolunteerUI = "/View/VolunteerUI.fxml";
    public final String AdminUI = "/View/AdminUI.fxml";
    public final String Council = "/View/Council.fxml";
    public final String CreateCouncil = "/View/CreateCouncil.fxml";
    public final String UpdateCouncil = "/View/UpdateCouncil.fxml";
    public final String DeleteCouncil = "/View/DeleteCouncil.fxml";
    public final String Users = "/View/Users.fxml";
    public final String CreateUser = "/View/CreateUser.fxml";
    public final String UpdateUser = "/View/UpdateUser.fxml";
    public final String DeleteUser = "/View/DeleteUser.fxml";
    public final String VolunteerAndCouncils = "/View/VolunteerAndCouncils.fxml";
    public final String Student = "/View/Student.fxml";
    public final String RegisterStudent = "/View/RegisterStudent.fxml";
    public final String UpdateStudent = "/View/UpdateStudent.fxml";
    public final String DeleteStudent = "/View/DeleteStudent.fxml";
    public final String StudentMobile = "/View/StudentMobile.fxml";
    public final String Lectures = "/View/Lectures.fxml";
    public final String AddLecture = "/View/AddLecture.fxml";
    public final String UpdateLecture = "/View/UpdateLecture.fxml";
    public final String DeleteLecture = "/View/DeleteLecture.fxml";
    public final String Attendance = "/View/Attendance.fxml";
    public final String ExcelAttendance = "/View/ExcelAttendance.fxml";


    public void navTo(Parent rootPane, String pathFxml)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource(pathFxml));
            rootPane.getScene().setRoot(root);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}