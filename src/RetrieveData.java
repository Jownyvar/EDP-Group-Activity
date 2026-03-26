
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RetrieveData {

    private final JTable studentRecordsTable;
    private final JTable courseListTbl;
    private final JTable enrollmentRecordsTbl;
    private final JComboBox studentIDCB;
    private final JComboBox courseIDCB;

    public RetrieveData(JTable studentRecordsTable, JTable courseListTbl, JTable enrollmentRecordsTbl, JComboBox studentIDCB, JComboBox courseIDCB) {
        this.studentRecordsTable = studentRecordsTable;
        this.courseListTbl = courseListTbl;
        this.enrollmentRecordsTbl = enrollmentRecordsTbl;
        this.studentIDCB = studentIDCB;
        this.courseIDCB = courseIDCB;
    }

    public void updateStudentsTable() {
        try {
            Statement st = DatabaseConnection.con.createStatement();
            DefaultTableModel dtm = (DefaultTableModel) studentRecordsTable.getModel();
            dtm.setRowCount(0);
            studentIDCB.removeAllItems();
            String sql = "SELECT * FROM " + Tables.students;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String[] datas = {rs.getString("StudentID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Program"), rs.getString("Year_Level")};
                studentIDCB.addItem(rs.getString("StudentID") + " - " + rs.getString("FirstName") + " " + rs.getString("LastName"));
                dtm.addRow(datas);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving students data: " + e.getMessage());
        }
    }

    public void updateCourseListTbl() {
        try {
            Statement st = DatabaseConnection.con.createStatement();
            DefaultTableModel dtm = (DefaultTableModel) courseListTbl.getModel();
            dtm.setRowCount(0);
            courseIDCB.removeAllItems();
            String sql = "SELECT * FROM " + Tables.courses;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String[] datas = {rs.getString("course_id"), rs.getString("course_code"), rs.getString("course_title"), rs.getString("units")};
                courseIDCB.addItem(rs.getString("course_id") + "    " + rs.getString("course_code") + " - " + rs.getString("course_title"));
                dtm.addRow(datas);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving courses data: " + e.getMessage());
        }
    }

    public void updateEnrollmentRecordsTbl() {
        try {
            Statement st = DatabaseConnection.con.createStatement();
            DefaultTableModel dtm = (DefaultTableModel) enrollmentRecordsTbl.getModel();
            dtm.setRowCount(0);
            String sql = "SELECT * FROM " + Tables.enrollments + " JOIN " + Tables.students;
            String sql2 = "SELECT s.FirstName + ' ' + s.LastName AS 'Student Name', c.course_title, e.semester, e.school_year FROM Enrollments e\n"
                    + "JOIN Students s ON e.student_id = s.studentID\n"
                    + "JOIN Courses c ON e.course_id = c.course_id";
            ResultSet rs = st.executeQuery(sql2);

            while (rs.next()) {
                String[] datas = {rs.getString("Student Name"), rs.getString("course_title"), rs.getString("semester"), rs.getString("school_year")};
                dtm.addRow(datas);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving courses data: " + e.getMessage());
        }
    }
}
