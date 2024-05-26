package schooldatabase.model;

public class Instructor {
    int id;
    String name;
    int departmentID;

    public Instructor(int id, String name, int departmentID) {
        this.id = id;
        this.name = name;
        this.departmentID = departmentID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(int id) {
        this.departmentID = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getDepartmentID() {
        return this.departmentID;
    }

    @Override
    public String toString() {
        return name;
    }

}
