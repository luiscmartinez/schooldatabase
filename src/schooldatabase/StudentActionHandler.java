package schooldatabase;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;

public class StudentActionHandler {
    private final StudentFormGenerator newStudentForm;
    private final StudentFileManager studentFileManager;

    public StudentActionHandler(StudentFormGenerator newStudentForm, StudentFileManager studentFileManager) {
        this.newStudentForm = newStudentForm;
        this.studentFileManager = studentFileManager;
    }

    public void handleAddStudent() {
        System.out.println("Form submitted!");
        System.out.println("First Name: " + newStudentForm.getFirstName());
        System.out.println("Last Name: " + newStudentForm.getLastName());
        final int studentID = studentFileManager.students.size() + 1;
        System.out.println("studentID: " + studentID);
        String firstName = newStudentForm.getFirstName();
        String lastName = newStudentForm.getLastName();
        String address = newStudentForm.getAddress();
        String city = newStudentForm.getCity();
        String zipcode = newStudentForm.getZipcode();
        String state = newStudentForm.getState();
        try {
            if (studentFileManager.addStudent(studentID, firstName, lastName, address, city, zipcode, state)) {
                System.out.println("Student added successfully!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Student ID: " + studentID + "\nStudent Name: " + firstName + " " + lastName + "\nAddress: "
                                + address + "\n\t   " + city + ", " + state + " " + zipcode,
                        ButtonType.OK);
                alert.setHeaderText("Student Added");
                newStudentForm.clearForm();
                alert.showAndWait();
            } else {
                // ! I don't think this make sense because when will the user be able to add
                // another student with the same id?
                System.out.println("Failed to add student!");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Student Already Exists ", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (EmptyFieldException EFE) {
            Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
            alert.showAndWait();
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
