
import java.sql.PreparedStatement;

public class Insert {

    public boolean saveStudent(String firstName, String lastName, String program, String yearLevel) {
        String sql = "INSERT INTO " + Tables.students + " VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = DatabaseConnection.con.prepareStatement(sql);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, program);
            pst.setString(4, yearLevel);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public boolean saveCourse(String courseCode, String courseTitle, String units) {
        String sql = "INSERT INTO " + Tables.courses + " VALUES (?,?,?)";
        try {
            PreparedStatement pst = DatabaseConnection.con.prepareStatement(sql);
            pst.setString(1, courseCode);
            pst.setString(2, courseTitle);
            pst.setString(3, units);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public boolean saveEnrollment(String studentID, String courseID, String semester, String schoolYear) {
        String sql = "INSERT INTO " + Tables.enrollments + " VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = DatabaseConnection.con.prepareStatement(sql);
            pst.setString(1, studentID);
            pst.setString(2, courseID);
            pst.setString(3, semester);
            pst.setString(4, schoolYear);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return false;
        }
    }

}
