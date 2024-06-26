package schooldatabase;

import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import schooldatabase.client.ZipCodeClient;
import schooldatabase.model.Student;
import schooldatabase.model.ZipCode;

public class StudentFormGenerator {

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField addressField;
    private TextField cityField;
    private TextField zipcodeField;
    private ComboBox<String> stateComboBox;
    private Button actionButton;

    public StudentFormGenerator() {
        // Initialize form components
        firstNameField = new TextField();
        lastNameField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        zipcodeField = new TextField();
        stateComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
                "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
                "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"));
        actionButton = new Button();
    }

    public GridPane createForm(String formTitle) {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);

        Label titleLabel = new Label(formTitle);
        titleLabel.setFont(new Font("Arial", 18));
        GridPane.setHalignment(titleLabel, HPos.CENTER);
        GridPane.setColumnSpan(titleLabel, 2);
        Button searchButton = new Button("Search Zipcode");
        searchButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                            System.out.println("Search zipcode: " + getZipcode());
                            ZipCodeClient apiRequest = new ZipCodeClient(getZipcode());
                            ZipCode zipCode = apiRequest.fetchZipCodeInfo();
                            zipCode.getPlaces()[0].getStateAbbreviation();
                            stateComboBox.setValue(zipCode.getPlaces()[0].getStateAbbreviation());
                            cityField.setText(zipCode.getPlaces()[0].getPlaceName());
                        } catch (Exception exception) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Not a valid zipcode", ButtonType.OK);
                            alert.showAndWait();
                        }
                    }
                });
        formPane.add(titleLabel, 0, 0);
        formPane.add(new Label("First Name:"), 0, 1);
        formPane.add(firstNameField, 1, 1);
        formPane.add(new Label("Last Name:"), 0, 2);
        formPane.add(lastNameField, 1, 2);
        formPane.add(new Label("Address:"), 0, 3);
        formPane.add(addressField, 1, 3);
        formPane.add(new Label("City:"), 0, 4);
        formPane.add(cityField, 1, 4);
        formPane.add(new Label("Zipcode:"), 0, 5);
        formPane.add(zipcodeField, 1, 5);
        formPane.add(new Label("State:"), 0, 6);
        formPane.add(stateComboBox, 1, 6);

        if (!actionButton.getText().isEmpty()) {
            formPane.add(actionButton, 1, 7);
            formPane.add(searchButton, 2, 5);
        } else {
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            addressField.setEditable(false);
            cityField.setEditable(false);
            zipcodeField.setEditable(false);
            stateComboBox.setDisable(true);
        }

        return formPane;
    }

    public void prepopulateForm(Student student) {
        firstNameField.setText(student.getFirstName());
        lastNameField.setText(student.getLastName());
        addressField.setText(student.getAddress());
        cityField.setText(student.getCity());
        zipcodeField.setText(student.getZip());
        stateComboBox.setValue(student.getState());
    }

    /**
     * Configures the action button with a label and an action.
     * 
     * @param label  The text label of the button.
     * @param action The action to be performed when the button is clicked.
     */
    public void configureActionButton(String label, Consumer<ActionEvent> action) {
        actionButton.setText(label);
        actionButton.setOnAction(action::accept);
    }

    // Getter methods
    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getCity() {
        return cityField.getText();
    }

    public String getZipcode() {
        return zipcodeField.getText();
    }

    public String getState() {
        return stateComboBox.getValue();
    }

    public void clearForm() {
        // Clear all text fields and reset the combo box
        firstNameField.clear();
        lastNameField.clear();
        addressField.clear();
        cityField.clear();
        zipcodeField.clear();
        stateComboBox.getSelectionModel().clearSelection();
    }
}
