package schooldatabase.model;

public class Enrollment implements Comparable<Enrollment> {
    int student_id, course_id, id;
    String year, semester;
    char grade;

    public Enrollment(int id, int course_id, int student_id, String year, String semester, char grade) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.grade = grade;
    }

    public void setCourseID(int course_id) {
        this.course_id = course_id;
    }

    public void setStudentID(int student_id) {
        this.student_id = student_id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public String getYear() {
        return this.year;
    }

    public String getSemester() {
        return this.semester;
    }

    public int getStudentID() {
        return this.student_id;
    }

    public int getCourseID() {
        return this.course_id;
    }

    public int getID() {
        return this.id;
    }

    public char getGrade() {
        return this.grade;
    }

    @Override
    public int compareTo(Enrollment o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
