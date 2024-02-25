package schooldatabase.model;

public class Enrollment {
    int SID, CID, EID;
    String year, semester;
    char grade;

    public Enrollment(int EID, int CID, int SID, String year, String semester, char grade) {
        this.SID = SID;
        this.CID = CID;
        this.EID = EID;
        this.year = year;
        this.semester = semester;
        this.grade = grade;
    }

    void setCID(int CID) {
        this.CID = CID;
    }

    void setSID(int SID) {
        this.SID = SID;
    }

    void setEID(int EID) {
        this.EID = EID;
    }

    void setYear(String year) {
        this.year = year;
    }

    void setSemester(String semester) {
        this.semester = semester;
    }

    void setGrade(char grade) {
        this.grade = grade;
    }

    public String getYear() {
        return this.year;
    }

    public String getSemester() {
        return this.semester;
    }

    public int getSID() {
        return this.SID;
    }

    public int getCID() {
        return this.CID;
    }

    public int getEID() {
        return this.EID;
    }

    public char getGrade() {
        return this.grade;
    }
}
