package schooldatabase;

import javafx.scene.layout.GridPane;

public class AddDepartmentView {
    private DepartmentFileManager departmentFileManager;
    private GridPane formPane;

    public AddDepartmentView(DepartmentFileManager departmentFileManager) {
        this.departmentFileManager = departmentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        // Initialize and configure the DepartmentFormGenerator
        DepartmentFormGenerator newDepartmentForm = new DepartmentFormGenerator();
        DepartmentActionHandler actionHandler = new DepartmentActionHandler(newDepartmentForm, departmentFileManager);
        newDepartmentForm.configureActionButton("Add Department", event -> actionHandler.handleAddDepartment());

        return newDepartmentForm.createFormPane();
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
