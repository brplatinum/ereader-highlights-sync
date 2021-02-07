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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    //UI Elements
    Button btnDeviceInfo;
    Button btnChooseDevicePath;
    Button btnExtractHighlights;

    ComboBox cmbDeviceType;

    Label lblDevicePath;


    Device device;

    DirectoryChooser directoryChooser;

    @Override
    public void start(Stage stage) {
        device = new Device();
        directoryChooser = new DirectoryChooser();

        GridPane grid = new GridPane();

        stage.setTitle("Ereader Highlights Sync");

        btnDeviceInfo = new Button();
        btnDeviceInfo.setText("Device");
        grid.add(btnDeviceInfo, 1, 0);

        ObservableList<String> deviceTypeOptions = FXCollections.observableArrayList(
                "Kindle",
                "Kobo"
        );

        cmbDeviceType = new ComboBox();
        cmbDeviceType.setItems(deviceTypeOptions);
        cmbDeviceType.valueProperty().addListener((observableValue, oldValue, newValue) -> device.setDeviceType((String) newValue));
        grid.add(cmbDeviceType, 2, 0);

        btnChooseDevicePath = new Button();
        btnChooseDevicePath.setText("Choose Device Path");
        btnChooseDevicePath.setOnAction(actionEvent -> {
            File file = directoryChooser.showDialog(stage);

            device.setPath(file.toString());

            lblDevicePath = new Label();
            lblDevicePath.setText(file.toString());
            grid.add(lblDevicePath, 4, 0);
        });
        grid.add(btnChooseDevicePath, 3, 0);

        btnExtractHighlights = new Button();
        btnExtractHighlights.setText("Extract Highlights");
        btnExtractHighlights.setOnAction(actionEvent -> {
            device.extractHighlights();
            device.exportToCSV();
        });
        grid.add(btnExtractHighlights, 5, 0);

        Scene scene = new Scene(grid, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}