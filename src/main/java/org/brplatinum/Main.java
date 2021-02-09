package org.brplatinum;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    //UI Elements
    Button btnDeviceInfo;
    Button btnChooseDevicePath;
    Button btnExtractHighlights;
    Button btnExportEmail;

    ComboBox cmbDeviceType;

    Label lblDevicePath;


    Device device;

    DirectoryChooser directoryChooser;

    @Override
    public void start(Stage stage) {
        device = new Device();
        directoryChooser = new DirectoryChooser();

        VBox layout = new VBox(20);

        stage.setTitle("Ereader Highlights Sync");

        btnDeviceInfo = new Button("Device");

        ObservableList<String> deviceTypeOptions = FXCollections.observableArrayList(
                "Kindle",
                "Kobo"
        );

        cmbDeviceType = new ComboBox();
        cmbDeviceType.setItems(deviceTypeOptions);
        cmbDeviceType.valueProperty().addListener((observableValue, oldValue, newValue) -> device.setDeviceType((String) newValue));

        btnChooseDevicePath = new Button("Choose Device Path");
        btnChooseDevicePath.setOnAction(actionEvent -> {
            File file = directoryChooser.showDialog(stage);

            device.setPath(file.toString());

            lblDevicePath = new Label(file.toString());
            layout.getChildren().add(lblDevicePath);
        });

        btnExtractHighlights = new Button("Extract Highlights");
        btnExtractHighlights.setOnAction(actionEvent -> {
            device.extractHighlights();
            device.exportToCSV();
        });

        btnExportEmail = new Button("Export to Email");
        btnExportEmail.setOnAction(actionEvent -> {
            EmailView.display();
        });

        layout.getChildren().addAll(btnDeviceInfo, cmbDeviceType, btnChooseDevicePath, btnExtractHighlights, btnExportEmail);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}