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
        String name = newInstructorForm.getName();
        String departmentName = newInstructorForm.getDepartment();
        if (name.isEmpty() || departmentName == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out all fields", ButtonType.OK);
            alert.setHeaderText("Error");
            alert.showAndWait();
            return;
        }
        try {
            // department
            int departmentID = instructorFileManager.getDepartmentByName(departmentName);

            if (instructorFileManager.addInstructor(name, departmentID)) {
                System.out.println("Instructor added successfully!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Instructor " + name + " from the " + departmentName + " department has been added",
                        ButtonType.OK);
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
