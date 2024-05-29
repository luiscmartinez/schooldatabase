package schooldatabase.model;

public class Student implements Comparable<Student> {
    int id;
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;

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

    @Override
    public int compareTo(Student o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
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

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZip() {
        return this.zip;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zip;
    }
}
