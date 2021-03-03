package org.brplatinum.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.brplatinum.model.Email;
import org.brplatinum.model.EmailServer;
import org.brplatinum.model.Encryption;

public class EmailView {
    GridPane emailLayout;

    Label lblDestinationEmail;
    TextField txtDestinationEmail;
    Button btnSendEmail;
    Button btnEmailSettings;

    public EmailView(){
        emailLayout = new GridPane();

        btnEmailSettings = new Button("Your Email Settings");
        emailLayout.add(btnEmailSettings, 0, 0, 3, 1);
        btnEmailSettings.setMinHeight(50);
        btnEmailSettings.setMinWidth(100);

        lblDestinationEmail = new Label("Send to:");
        emailLayout.add(lblDestinationEmail, 0, 1, 1, 1);

        txtDestinationEmail = new TextField();
        emailLayout.add(txtDestinationEmail, 1, 1, 1, 1);
        txtDestinationEmail.setMinHeight(50);
        txtDestinationEmail.setMinWidth(100);

        btnSendEmail = new Button("Send");
        emailLayout.add(btnSendEmail, 1, 2, 1, 1);
        btnSendEmail.setMinHeight(50);
        btnSendEmail.setMinWidth(100);
    }

    public Pane getView() {
        return emailLayout;
    }
}
