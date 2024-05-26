package schooldatabase;

import javafx.scene.layout.GridPane;

public class AddInstructorView {
    private InstructorFileManager instructorFileManager;
    private DepartmentFileManager departmentFileManager;
    private GridPane formPane;

    public AddInstructorView(InstructorFileManager instructorFileManager, DepartmentFileManager departmentFileManager) {
        this.instructorFileManager = instructorFileManager;
        this.departmentFileManager = departmentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        // Initialize and configure the InstructorFormGenerator
        InstructorFormGenerator newInstructorForm = new InstructorFormGenerator(
                departmentFileManager.getDepartmentNames());

        InstructorActionHandler actionHandler = new InstructorActionHandler(newInstructorForm, instructorFileManager);

        newInstructorForm.configureActionButton("Add Instructor", event -> actionHandler.handleAddInstructor());

        return newInstructorForm.createForm("Create Instructor");
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
