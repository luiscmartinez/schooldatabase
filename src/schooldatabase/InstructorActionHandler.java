package schooldatabase;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class InstructorActionHandler {
    private final InstructorFormGenerator newInstructorForm;
    private final InstructorFileManager instructorFileManager;

    public InstructorActionHandler(InstructorFormGenerator newInstructorForm,
            InstructorFileManager instructorFileManager) {
        this.newInstructorForm = newInstructorForm;
        this.instructorFileManager = instructorFileManager;
    }

    public void handleAddInstructor() {
        System.out.println("Form submitted!");
        System.out.println("Instructor Name: " + newInstructorForm.getName());
        final int instructorID = instructorFileManager.instructors.size() + 1;
        System.out.println("instructorID: " + instructorID);
        String name = newInstructorForm.getName();
        String department = newInstructorForm.getDepartment();
        try {
            if (instructorFileManager.addInstructor(instructorID, name, department)) {
                System.out.println("Instructor added successfully!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Instructor " + name + " from the " + department + " has been added", ButtonType.OK);
                alert.setHeaderText("Instructor Added");
                newInstructorForm.clearForm();
                alert.showAndWait();
            } else {
                System.out.println("Failed to add instructor!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
