package schooldatabase.model;

public class Course implements Comparable<Course> {
    int courseID;
    String name, description, department, instructor;

    public Course(int courseID, String name, String description, String department, String instructor) {
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        this.department = department;
        this.instructor = instructor;
    }

    public void setID(int courseID) {
        this.courseID = courseID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String toString() {
        return courseID + "," + name + "," + description + "," + department + "," + instructor;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDepartment() {
        return department;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public int compareTo(Course o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}