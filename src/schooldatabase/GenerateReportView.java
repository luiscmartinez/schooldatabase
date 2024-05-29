package schooldatabase;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import schooldatabase.model.Enrollment;
import schooldatabase.model.ReportEntry;

public class GenerateReportView {
    private EnrollmentFileManager enrollmentFileManager;
    private StudentFileManager studentFileManager;
    private CourseFileManager courseFileManager;
    private InstructorFileManager instructorFileManager;
    private DepartmentFileManager departmentFileManager;
    private VBox formPane;

    public GenerateReportView(EnrollmentFileManager enrollmentFileManager, StudentFileManager studentFileManager,
            CourseFileManager courseFileManager, InstructorFileManager instructorFileManager,
            DepartmentFileManager departmentFileManager) {
        this.enrollmentFileManager = enrollmentFileManager;
        this.studentFileManager = studentFileManager;
        this.courseFileManager = courseFileManager;
        this.instructorFileManager = instructorFileManager;
        this.departmentFileManager = departmentFileManager;
        this.formPane = createFormPane();
    }

    private VBox createFormPane() {
        Label reportTitle = new Label("Report");
        Label courseIDLabel = new Label("Course ID: ");
        TextField courseIDField = new TextField();
        Label semesterLabel = new Label("Semester: ");
        ComboBox<String> semesterCB = new ComboBox<>();
        semesterCB.getItems().addAll("Spring", "Summer", "Fall", "Winter");
        Label yearLabel = new Label("Year: ");
        TextField yearField = new TextField();
        Label fieldNames = new Label("StudentID   Student Name   Semester   Year   Grade");
        VBox labelBox = new VBox(10, courseIDLabel, semesterLabel, yearLabel);
        VBox fieldBox = new VBox(courseIDField, semesterCB, yearField);

        HBox inputBox = new HBox(labelBox, fieldBox);
        inputBox.setAlignment(Pos.CENTER);

        TableView<ReportEntry> tableView = new TableView<>();
        TableColumn<ReportEntry, Integer> cidCol = new TableColumn<>("Course ID");
        TableColumn<ReportEntry, Integer> sidCol = new TableColumn<>("Student ID");
        TableColumn<ReportEntry, String> nameCol = new TableColumn<>("Student Name");
        TableColumn<ReportEntry, String> semesterCol = new TableColumn<>("Semester");
        TableColumn<ReportEntry, Integer> yearCol = new TableColumn<>("Year");
        TableColumn<ReportEntry, Character> gradeCol = new TableColumn<>("Grade");

        tableView.getColumns().addAll(cidCol, sidCol, nameCol, semesterCol, yearCol, gradeCol);

        // Set up cell value factories
        cidCol.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        sidCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        Button generateReportButton = new Button("Generate Report");

        generateReportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String courseIDText = courseIDField.getText();
                    String yearText = yearField.getText();
                    String semesterText = semesterCB.getValue();

                    if (courseIDText.isEmpty() || yearText.isEmpty() || semesterText == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled out.", ButtonType.OK);
                        alert.showAndWait();
                        return;
                    }

                    int courseID = Integer.parseInt(courseIDText);

                    ArrayList<Enrollment> enrollments = enrollmentFileManager.getEnrollments(courseID, yearText,
                            semesterText);
                    ObservableList<ReportEntry> reportEntries = FXCollections.observableArrayList();

                    for (Enrollment enrollment : enrollments) {
                        int studentID = enrollment.getStudentID();
                        int courseIDVal = enrollment.getCourseID();
                        char grade = enrollment.getGrade();
                        String studentName = studentFileManager.getStudent(studentID).getName();
                        String semester = enrollment.getSemester();
                        String yearVal = enrollment.getYear();

                        ReportEntry entry = new ReportEntry(courseIDVal, studentID, studentName, semester, yearVal,
                                grade);
                        reportEntries.add(entry);
                    }

                    tableView.setItems(reportEntries); // Update table items

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Course ID or Year.",
                            ButtonType.OK);
                    alert.showAndWait();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        VBox reportVBox = new VBox(10, reportTitle, inputBox, generateReportButton, fieldNames, tableView);
        reportVBox.setAlignment(Pos.CENTER);
        return reportVBox;
    }

    public VBox getFormPane() {
        return this.formPane;
    }
}
