package org.brplatinum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmailView {

    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);

        TextField txtHostname = new TextField();
        TextField txtUserEmail = new TextField();
        PasswordField pwfUserPassword = new PasswordField();
        TextField txtDestinationEmail = new TextField();
        Spinner spnPort = new Spinner(1, Integer.MAX_VALUE, 1);
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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblUserEmail, txtUserEmail, lblUserPassword, pwfUserPassword, lblHostName, txtHostname, lblPort, spnPort, lblEncryption, rdbTLS, rdbSSL, rdbNone, lblDestinationEmail, txtDestinationEmail, btnSendEmail);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
