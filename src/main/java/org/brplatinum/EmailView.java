package org.brplatinum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmailView {


    public static void display() {
        EmailServer server = new EmailServer();
        String destinationEmail;

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);

        TextField txtHostname = new TextField();
        txtHostname.setOnKeyReleased(actionEvent -> {
            server.setHostname(txtHostname.getText());
        });


        TextField txtUserEmail = new TextField();
        txtUserEmail.setOnKeyReleased(actionEvent -> {
            server.setUsername(txtUserEmail.getText());
        });

        PasswordField pwfUserPassword = new PasswordField();
        pwfUserPassword.setOnKeyReleased(actionEvent -> {
            server.setPassword(pwfUserPassword.getText());
        });

        TextField txtDestinationEmail = new TextField();
        txtDestinationEmail.setOnKeyReleased(actionEvent -> {
            destinationEmail = txtDestinationEmail.getText();
        });

        Spinner spnPort = new Spinner(1, Integer.MAX_VALUE, 1);
        spnPort.setOnKeyReleased(actionEvent -> {
            server.setPort(Integer.parseInt(spnPort.getAccessibleText()));
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
            Email.sendEmail(server, destinationEmail);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblUserEmail, txtUserEmail, lblUserPassword, pwfUserPassword, lblHostName, txtHostname, lblPort, spnPort, lblEncryption, rdbTLS, rdbSSL, rdbNone, lblDestinationEmail, txtDestinationEmail, btnSendEmail);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
