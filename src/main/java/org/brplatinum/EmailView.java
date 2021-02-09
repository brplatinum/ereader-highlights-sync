package org.brplatinum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmailView {

    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);

        TextField txtUserEmail = new TextField();
        TextField txtUserPassword = new TextField();
        TextField txtDestinationEmail = new TextField();

        Label lblUserEmail = new Label("Your Email:");
        Label lblUserPassword = new Label("Your Password:");
        Label lblDestinationEmail = new Label("Destination Email:");

        Button btnSendEmail = new Button("Send");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lblUserEmail, txtUserEmail, lblUserPassword, txtUserPassword, lblDestinationEmail, txtDestinationEmail, btnSendEmail);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
