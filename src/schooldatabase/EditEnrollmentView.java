package schooldatabase;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Enrollment;

public class EditEnrollmentView {
    private EnrollmentFileManager enrollmentFileManager;
    private StudentFileManager studentFileManager;
    private CourseFileManager courseFileManager;
    private GridPane formPane;

    public EditEnrollmentView(EnrollmentFileManager enrollmentFileManager, StudentFileManager studentFileManager,
            CourseFileManager courseFileManager) {
        this.enrollmentFileManager = enrollmentFileManager;
        this.studentFileManager = studentFileManager;
        this.courseFileManager = courseFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        Label enrollmentLabel = new Label("Enrollment ID");
        TextField enrollmentIdInput = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        Enrollment enrollment;
                        final int enrollmentId = Integer.parseInt(enrollmentIdInput.getText());
                        try {
                            enrollment = enrollmentFileManager.getEnrollment(enrollmentId);
                            if (enrollment != null) {
                                EnrollmentFormGenerator enrollmentForm = new EnrollmentFormGenerator();
                                enrollmentForm.prepopulateForm(enrollment.getSID(), enrollment.getCID(),
                                        enrollment.getYear(), enrollment.getSemester(), enrollment.getGrade());

                                EnrollmentActionHandler actionHandler = new EnrollmentActionHandler(enrollmentForm,
                                        enrollmentFileManager, studentFileManager, courseFileManager);

                                enrollmentForm.configureActionButton("Update Enrollment",
                                        event -> actionHandler.handleUpdateEnrollment(enrollmentId));
                                formPane.add(enrollmentForm.createFormPane("Edit Enrollment Form"), 0, 1);
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Enrollment was not Found",
                                        ButtonType.OK);
                                alert.showAndWait();
                            }
                        } catch (EmptyFieldException EFE) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        } catch (Exception exc) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        }
                    }
                });
        formPane.add(enrollmentLabel, 0, 0);
        formPane.add(enrollmentIdInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
