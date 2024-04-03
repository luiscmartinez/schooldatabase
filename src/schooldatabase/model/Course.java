package schooldatabase.model;

public class Course implements Comparable<Course> {
    int courseID;
    String name, description;

    public Course(int courseID, String name, String description) {
        this.courseID = courseID;
        this.name = name;
        this.description = description;
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

    public String toString() {
        return courseID + "," + name + "," + description;
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

    @Override
    public int compareTo(Course o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}