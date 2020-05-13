package Models;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBModel {
    private static String currentUser = "admin";
    private static String password = "admin";

    public static String getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(String currentUser) {
        DBModel.currentUser = currentUser;
    }

    public static String getPassword() {
        return password;
    }
    public static void setPassword(String password) {
        DBModel.password = password;
    }

    //here our queries method
    private DBModel(){
        schemaConnect("public");
}
    private static DBModel dbmodel = null;
    Connection con = null;


    public static DBModel getModel()
    {
        if (dbmodel == null)
        {
            dbmodel = new DBModel();
        }
        return dbmodel;
    }

    public void connect(String username, String password){
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setDatabaseName("ReligiousCouncils");
        source.setUser(username);
        source.setPassword(password);

        try {
            con = source.getConnection();
            System.out.println("Connected to database");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }


    public void schemaConnect(String schema){
        String sql = "set search_path to '"+ schema + "'";
        Statement s1 = null;
        try {
            connect(getCurrentUser(), getPassword());
            s1 = con.createStatement();
            s1.execute(sql);
            System.out.println("Connected to schema "+ schema);
        } catch (SQLException ex) {
        }finally{
            try {
                s1.close();
            } catch (SQLException ex) {
            }

        }
    }

    public boolean checkVolunteer(String userName, String password){
        String query = "select * from متطوع where اسم_المتطوع = ? and كلمة_المرور = ?";

        try {
            PreparedStatement pstms = con.prepareStatement(query);
            pstms.setString(1, userName);
            pstms.setString(2, password);
            ResultSet rs = pstms.executeQuery();
            while (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void closeEverything() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exit() {
        closeEverything();
        System.out.println("Exiting... \nBye!");
        System.exit(0);
    }

    public ArrayList<String> getCouncils() {
        ArrayList<String> result = new ArrayList<>();
        String query = "select اسم_المجلس from مجلس;";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getCouncilFields(String CouncilName) {
        ArrayList<String> result = new ArrayList<>();
        String query = "select * from مجلس where اسم_المجلس = '" + CouncilName + "';";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result.add(rs.getString(1));
                result.add(rs.getString(2));
                result.add(rs.getString(3));
                result.add(rs.getString(4));
                result.add(rs.getString(5));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int UpdateCouncil(String old_name, String new_name, String Subject, String Book, String ShaikhName, String Mosque) {
        String query1 = "update مجلس set اسم_المجلس = '" + new_name + "' where اسم_المجلس = '" + old_name + "';";
        String query2 = "update مجلس set الموضوع = '" + Subject + "' where اسم_المجلس = '" + old_name + "';";
        String query3 = "update مجلس set الكتاب = '" + Book + "' where اسم_المجلس = '" + old_name + "';";
        String query4 = "update مجلس set اسم_الشيخ = '" + ShaikhName + "' where اسم_المجلس = '" + old_name + "';";
        String query5 = "update مجلس set المسجد = '" + Mosque + "' where اسم_المجلس = '" + old_name + "';";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query1) > 0 | stmt.executeUpdate(query2) > 0 | stmt.executeUpdate(query3) > 0
                    | stmt.executeUpdate(query4) > 0 | stmt.executeUpdate(query5) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int createCouncil(String CouncilName, String Subject, String Book, String ShaikhName, String Mosque) {
        String query = "insert into مجلس values ('" + CouncilName + "', " +
                "'" + Subject + "', " +
                "'" + Book + "', " +
                "'" + ShaikhName + "', " +
                "'" + Mosque + "');";
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int DeleteCouncil(String councilName) {
        String query = "Delete from مجلس where اسم_المجلس = '" + councilName + "';";
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int createVolunteer(String username, String password) {
        String query3 = "insert into \"متطوع\" values ('" + username + "' , '" + password + "');";
        String query1 = "create role " + username + " with login password '" + password + "';";
        String query2 = "grant volunteer to " + username + ";";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query3) > 0){
                stmt.executeUpdate(query1);
                stmt.executeUpdate(query2);
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getVolunteers() {
        ArrayList<String> result = new ArrayList<>();
        String query = "select اسم_المتطوع from متطوع;";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getVolunteerPassword(String vol_name) {
        String query = "select كلمة_المرور from متطوع where اسم_المتطوع = '" + vol_name + "';";
        String result = "";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            result = rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int UpdateVolunteer(String old_name, String new_name, String new_password) {
        String query1 = "update متطوع set اسم_المتطوع = '" + new_name + "' where اسم_المتطوع = '" + old_name + "';";
        String query2 = "update متطوع set كلمة_المرور = '" + new_password + "' where اسم_المتطوع = '" + old_name + "';";
        String query3 = "alter user " + old_name + " rename to " + new_name + ";";
        String query4 = "alter user " + new_name + " with password '" + new_password + "';";
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query1) > 0 | stmt.executeUpdate(query2) > 0){
                if(!old_name.equals(new_name)){
                    stmt.executeUpdate(query3);
                }
                stmt.executeUpdate(query4);
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int DeleteVolunteer(String vol_name) {
        String query = "delete from متطوع where اسم_المتطوع = '" + vol_name + "';";
        String query2 = "drop role " + vol_name + ";";
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                stmt.executeUpdate(query2);
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int VolunteerJoinCouncil(String vol_name, String council_name) {
        String query = "insert into متطوعو_المجالس values ('" + vol_name + "', '" + council_name + "');";
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*public boolean checkStudentID(String studentID) {
        String query = "select * from طالب where \"رقم_الطالب\" = '" + studentID + "';";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/

    public int RegisterStudent(String studentName, String parent, String grandParent, String family,
                               String studentID, String place, String mobile) {
        String query1 = "insert into الاسم values ('" + studentID + "', " +
                "'" + studentName + "', " +
                "'" + parent + "', " +
                "'" + grandParent + "', " +
                "'" + family + "');";

        String query2 = "insert into طالب values ('" + studentID + "', " +
                "'" + place + "');";

        String query3 = "insert into الجوال values ('" + studentID + "', " +
                "'" + mobile + "');";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query2) > 0 & stmt.executeUpdate(query1) > 0 & stmt.executeUpdate(query3) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getStudents() {
        ArrayList<String> result = new ArrayList<>();
        String query = "select رقم_الطالب from طالب;";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<String> getStudentInfo(String studentID) {
        ArrayList<String> result = new ArrayList<>();
        String query1 = "select * from طالب where \"رقم_الطالب\" = '" + studentID + "';" ;
        String query2 = "select * from الاسم where \"رقم_الطالب\" = '" + studentID + "';" ;
        String query3 = "select * from الجوال where \"رقم_الطالب\" = '" + studentID + "';" ;

        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                result.add(rs.getString(2));
            }
            rs = stmt.executeQuery(query2);
            while (rs.next()) {
                result.add(rs.getString(2));
                result.add(rs.getString(3));
                result.add(rs.getString(4));
                result.add(rs.getString(5));
            }
            rs = stmt.executeQuery(query3);
            while (rs.next()) {
                result.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int UpdateStudentInfo(String old_id, String studentName, String parent,
                                 String grandParent, String family, String mobile, String place) {
        String query2 = "update طالب set مكان_السكن = '" + place + "' where رقم_الطالب = '" + old_id + "';";
        String query3 = "update الاسم set اسم_الطالب = '" + studentName + "' where رقم_الطالب = '" + old_id + "';";
        String query4 = "update الاسم set اسم_الأب = '" + parent + "' where رقم_الطالب = '" + old_id + "';";
        String query5 = "update الاسم set اسم_الجد = '" + grandParent + "' where رقم_الطالب = '" + old_id + "';";
        String query6 = "update الاسم set اسم_العائلة = '" + family + "' where رقم_الطالب = '" + old_id + "';";
        String query7 = "update الجوال set رقم_الجوال = '" + mobile + "' where رقم_الطالب = '" + old_id + "';";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query2) > 0 | stmt.executeUpdate(query3) > 0 |
                    stmt.executeUpdate(query4) > 0 | stmt.executeUpdate(query5) > 0 | stmt.executeUpdate(query6) > 0 |
                    stmt.executeUpdate(query7) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public ArrayList<String> getMobileNumbers(String studentID) {
        ArrayList<String> result = new ArrayList<>();
        String query = "select * from الجوال where \"رقم_الطالب\" = '" + studentID + "';";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int addMobile(String studentID, String mobile) {
        String query = "insert into الجوال values ('" + studentID + "', '" + mobile + "');";
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteMobile(String studentID, String mobile) {
        String query = "delete from الجوال where \"رقم_الطالب\" = '" + studentID + "' and \"رقم_الجوال\" = '" + mobile + "';";
        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteStudent(String studentID) {
        String query1 = "delete from الاسم where \"رقم_الطالب\" = '" + studentID + "';";
        String query2 = "delete from الجوال where \"رقم_الطالب\" = '" + studentID + "';";
        String query3 = "delete from طالب where \"رقم_الطالب\" = '" + studentID + "';";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query1) > 0 & stmt.executeUpdate(query2) > 0 & stmt.executeUpdate(query3) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int addLecture(String lectureNumber, String council, String shaikh, String subject, String place) {
        String query1 = "insert into محاضرة values ('" + lectureNumber + "', '" +
                council + "', '" + shaikh + "', '" + subject + "', '" + place + "');";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query1) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getShaikhName(String councilName) {
        String result = "";
        String query = "select اسم_الشيخ from مجلس where \"اسم_المجلس\" = '" + councilName + "';";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getPlace(String shaikhName) {
        String query = "select * from الشيخ where \"اسم_الشيخ\" = '" + shaikhName + "';";
        String result = "";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result = rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getLectureNumbers(String councilName) {
        ArrayList<String> result = new ArrayList<>();
        String query = "select * from محاضرة where \"اسم_المجلس\" = '" + councilName + "' order by رقم_المحاضرة;";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int UpdateLecture(String lectureNumber, String councilName, String subject, String place) {
        String query1 = "update محاضرة set العنوان = '" + subject + "' where \"اسم_المجلس\" = '" + councilName + "' and \"رقم_المحاضرة\" = " + lectureNumber +";";
        String query2 = "update محاضرة set المكان = '" + place + "' where \"اسم_المجلس\" = '" + councilName + "' and \"رقم_المحاضرة\" = " + lectureNumber +";";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query1) > 0 | stmt.executeUpdate(query2) > 0 ){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getSubject(String councilName, String lectureNumber) {
        String query = "select * from محاضرة where \"اسم_المجلس\" = '" + councilName + "' and \"رقم_المحاضرة\" = " + lectureNumber +";";
        String result = "";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result = rs.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int DeleteLecture(String councilName, String lectureNumber) {
        String query = "delete from محاضرة where \"اسم_المجلس\" = '" + councilName + "' and \"رقم_المحاضرة\" = " + lectureNumber +";";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int registerAttendance(String council, String lectureNumber, String studentID, String studentName,
                                  String parent, String grandParent, String family, String mobile) {
        String query = "insert into حضور_وغياب values ('" + council + "', " +
                "'" + lectureNumber + "', " +
                "'" + studentID + "', " +
                "'" + studentName + "', " +
                "'" + parent + "', " +
                "'" + grandParent + "', " +
                "'" + family + "', " +
                "'" + mobile + "');";

        try (Statement stmt = con.createStatement()){
            if(stmt.executeUpdate(query) > 0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getStudentIDFromName(String studentName, String parent, String grandParent, String family) {
        String query = "select رقم_الطالب from الاسم where \"اسم_الطالب\" = '" + studentName + "' and \"اسم_الأب\" = '" + parent +
                "' and \"اسم_الجد\" = '" + grandParent + "' and \"اسم_العائلة\" = '" + family + "';";
        String result = "";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getStudentIDFromMobile(String mobileNumber) {
        String result = "";
        String query = "select * from الجوال where \"رقم_الجوال\" = '" + mobileNumber + "';";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result = rs.getString(1);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*public boolean checkStudentName(String studentName, String parent, String grandParent, String family) {
        String query = "select * from الاسم where اسم_الطالب = ? and اسم_الأب = ? and اسم_الجد = ? and اسم_العائلة = ?";

        try {
            PreparedStatement pstms = con.prepareStatement(query);
            pstms.setString(1, studentName);
            pstms.setString(2, parent);
            pstms.setString(3, grandParent);
            pstms.setString(4, family);
            ResultSet rs = pstms.executeQuery();
            while (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/
}
