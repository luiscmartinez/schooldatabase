package schooldatabase;

import javafx.scene.layout.GridPane;

public class AddStudentView {
    private StudentFileManager studentFileManager;
    private GridPane formPane;

    public AddStudentView(StudentFileManager studentFileManager) {
        this.studentFileManager = studentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        // Initialize and configure the StudentFormGenerator
        StudentFormGenerator newStudentForm = new StudentFormGenerator();
        StudentActionHandler actionHandler = new StudentActionHandler(newStudentForm, studentFileManager);
        newStudentForm.configureActionButton("Add Student", event -> actionHandler.handleAddStudent());

        return newStudentForm.createForm("New Student Form");
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
