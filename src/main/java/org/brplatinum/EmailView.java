package org.brplatinum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmailView {
    private static EmailServer server = new EmailServer();
    private static String destinationEmail;

    private static TextField txtHostname = new TextField();
    private static TextField txtUserEmail = new TextField();
    private static PasswordField pwfUserPassword = new PasswordField();
    private static TextField txtDestinationEmail = new TextField();
    private static Spinner<String> spnPort = new Spinner<>(0, 65535, 1);

    public static void display() {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);

        spnPort.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                spnPort.getEditor().setText(oldValue);
            } else if (!newValue.isEmpty()){
                if (Integer.parseInt(newValue) > 65535 || Integer.parseInt(newValue) < 0) {
                    spnPort.getEditor().setText(oldValue);
                }
            }
        });

        spnPort.setOnKeyReleased(actionEvent -> {
            if (!spnPort.getValue().isEmpty()) {
                server.setPort(Integer.parseInt(spnPort.getValue()));
            }
        });

        spnPort.setEditable(true);

        ToggleGroup tggEncryptionTypes = new ToggleGroup();

        RadioButton rdbTLS = new RadioButton("TLS");
        rdbTLS.setToggleGroup(tggEncryptionTypes);
        rdbTLS.setSelected(true);
        RadioButton rdbSSL = new RadioButton("SSL");
        rdbSSL.setToggleGroup(tggEncryptionTypes);
        RadioButton rdbNone = new RadioButton("None");
        rdbNone.setToggleGroup(tggEncryptionTypes);

        Label lblHostName = new Label("Hostname:");
        Label lblUserEmail = new Label("Your Email:");
        Label lblUserPassword = new Label("Your Password:");
        Label lblDestinationEmail = new Label("Destination Email:");
        Label lblPort = new Label("Port:");
        Label lblEncryption = new Label("Encryption");


        Button btnSendEmail = new Button("Send");
        btnSendEmail.setOnAction(actionEvent -> {
            EmailServer server = new EmailServer(txtHostname.getText(), txtUserEmail.getText(), pwfUserPassword.getText(), spnPort.getValue(), Encryption.valueOf(tggEncryptionTypes.getSelectedToggle().getUserData().toString().toUpperCase()));
            Email.sendEmail(server, destinationEmail);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblUserEmail, txtUserEmail, lblUserPassword, pwfUserPassword, lblHostName, txtHostname, lblPort, spnPort, lblEncryption, rdbTLS, rdbSSL, rdbNone, lblDestinationEmail, txtDestinationEmail, btnSendEmail);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
