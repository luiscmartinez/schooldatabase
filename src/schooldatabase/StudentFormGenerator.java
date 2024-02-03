package schooldatabase;

import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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

    public GridPane createForm() {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        // Adding form fields to the pane
        formPane.add(new Label("First Name:"), 0, 0);
        formPane.add(firstNameField, 1, 0);
        formPane.add(new Label("Last Name:"), 0, 1);
        formPane.add(lastNameField, 1, 1);
        formPane.add(new Label("Address:"), 0, 2);
        formPane.add(addressField, 1, 2);
        formPane.add(new Label("City:"), 0, 3);
        formPane.add(cityField, 1, 3);
        formPane.add(new Label("Zipcode:"), 0, 4);
        formPane.add(zipcodeField, 1, 4);
        formPane.add(new Label("State:"), 0, 5);
        formPane.add(stateComboBox, 1, 5);

        if (!actionButton.getText().isEmpty()) {
            formPane.add(actionButton, 1, 6);
        }

        return formPane;
    }

    public void prepopulateForm(String firstName, String lastName, String address, String city, String zipcode,
            String state) {
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
        addressField.setText(address);
        cityField.setText(city);
        zipcodeField.setText(zipcode);
        stateComboBox.setValue(state);
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
}
