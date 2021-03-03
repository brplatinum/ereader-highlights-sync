package org.brplatinum.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ReadwiseView {
    GridPane readwiseLayout;

    Label lblAccessToken;
    TextField txtAccessToken;
    Button btnSendHighlights;

    public ReadwiseView(){
        readwiseLayout = new GridPane();
        
        lblAccessToken = new Label("Send to:");
        readwiseLayout.add(lblAccessToken, 0, 0, 1, 1);

        txtAccessToken = new TextField();
        readwiseLayout.add(txtAccessToken, 0, 1, 1, 1);
        txtAccessToken.setMinHeight(50);
        txtAccessToken.setMinWidth(100);

        btnSendHighlights = new Button("Send");
        readwiseLayout.add(btnSendHighlights, 0, 2, 1, 1);
        btnSendHighlights.setMinHeight(50);
        btnSendHighlights.setMinWidth(100);
    }

    public Pane getView() {
        return readwiseLayout;
    }
}
