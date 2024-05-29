package schooldatabase.model;

public class ReportEntry {
    private int courseID;
    private int studentID;
    private String studentName;
    private String semester;
    private String year;
    private char grade;

    public ReportEntry(int courseID, int studentID, String studentName, String semester, String year, char grade) {
        this.courseID = courseID;
        this.studentID = studentID;
        this.studentName = studentName;
        this.semester = semester;
        this.year = year;
        this.grade = grade;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return year;
    }

    public char getGrade() {
        return grade;
    }
}
