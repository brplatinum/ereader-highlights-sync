package org.brplatinum.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.brplatinum.model.Device;
import org.brplatinum.model.DeviceType;

public class ReadwiseView {
    private GridPane readwiseLayout;

    private Label lblAccessToken;
    private TextField txtAccessToken;
    private Button btnSendHighlights;

    public ReadwiseView(){
        readwiseLayout = new GridPane();
        
        lblAccessToken = new Label("Send to:");
        readwiseLayout.add(lblAccessToken, 0, 0, 1, 1);

        txtAccessToken = new TextField();
        readwiseLayout.add(txtAccessToken, 1, 0, 1, 1);
        txtAccessToken.setMinHeight(50);
        txtAccessToken.setMinWidth(100);

        btnSendHighlights = new Button("Send");
        readwiseLayout.add(btnSendHighlights, 2, 0, 1, 1);
        btnSendHighlights.setMinHeight(50);
        btnSendHighlights.setMinWidth(100);

        btnSendHighlights.setOnAction(actionEvent -> {
            Device device = new Device();
//            device.extractHighlights();
//            device.exportToCSV();
        });
    }

    public Pane getView() {
        return readwiseLayout;
    }
}
