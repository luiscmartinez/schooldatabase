package schooldatabase.model;

public class Student implements Comparable<Student> {
    public int id;
    // delete after prepopulating form with student object instead
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String state;
    public String zip;

    // Constructor
    public Student(int id, String firstName, String lastName, String address, String city, String state, String zip) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    // TODO: Compare what??
    public int compareTo(Student o) {
        return this.id - o.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zip;
    }
}
