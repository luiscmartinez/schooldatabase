package schooldatabase.model;

public class Course implements Comparable<Course> {
    int courseID, instructor_id, department_id;
    String name, description;

    public Course(int courseID, String name, String description, int department_id, int instructor) {
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        this.department_id = department_id;
        this.instructor_id = instructor;
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

    public void setDepartmentID(int department) {
        this.department_id = department;
    }

    public void setInstructor(int instructorID) {
        this.instructor_id = instructorID;
    }

    public String toString() {
        return courseID + "," + name + "," + description + "," + department_id + "," + instructor_id;
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

    public int getDepartmentID() {
        return department_id;
    }

    public int getInstructor() {
        return instructor_id;
    }

    @Override
    public int compareTo(Course o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}