package schooldatabase.model;

public class Instructor {
    int id;
    String name;
    String department;

    public Instructor(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDepartment() {
        return this.department;
    }

}
