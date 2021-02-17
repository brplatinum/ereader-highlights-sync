package org.brplatinum;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class DeviceInfoView {

    Label lblDeviceInfo;
    Button btnChooseDevicePath;
    Button btnExtractHighlights;
    Button btnExportEmail;
    ComboBox cmbDeviceType;
    Device device;
    String deviceDirectory;
    DirectoryChooser directoryChooser;
    GridPane deviceInfoLayout;


    public DeviceInfoView(Stage stage) {
        deviceInfoLayout = new GridPane();
        deviceInfoLayout.setPadding(new Insets(10, 10, 10, 10));
        deviceInfoLayout.setVgap(10);
        deviceInfoLayout.setHgap(10);
        deviceInfoLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        deviceInfoLayout.setAlignment(Pos.CENTER);

        directoryChooser = new DirectoryChooser();


        lblDeviceInfo = new Label("Device Info:");
        deviceInfoLayout.add(lblDeviceInfo, 0, 0, 2, 1);
        GridPane.setHalignment(lblDeviceInfo, HPos.CENTER);


        ObservableList<String> deviceTypeOptions = FXCollections.observableArrayList(
                "Kindle",
                "Kobo"
        );

        cmbDeviceType = new ComboBox();
        cmbDeviceType.setItems(deviceTypeOptions);
        cmbDeviceType.getSelectionModel().selectFirst();
        cmbDeviceType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cmbDeviceType.setMinWidth(100);
        GridPane.setHgrow(cmbDeviceType, Priority.SOMETIMES);
        deviceInfoLayout.add(cmbDeviceType, 0, 1, 1, 1);

        btnChooseDevicePath = new Button("Choose Device Path");
        btnChooseDevicePath.setOnAction(actionEvent -> {
            File file = directoryChooser.showDialog(stage);

            if (file != null) {
                deviceDirectory = file.toString();
            }
        });
        GridPane.setHgrow(btnChooseDevicePath, Priority.ALWAYS);
        btnChooseDevicePath.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnChooseDevicePath.setMinWidth(100);
        deviceInfoLayout.add(btnChooseDevicePath, 1, 1, 1, 1);

        btnExtractHighlights = new Button("Extract Highlights");
        btnExtractHighlights.setOnAction(actionEvent -> {
            device = new Device(DeviceType.valueOf(cmbDeviceType.getValue().toString().toUpperCase()), deviceDirectory);
            device.extractHighlights();
            device.exportToCSV();
        });
        btnExtractHighlights.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(btnExtractHighlights, Priority.ALWAYS);
        deviceInfoLayout.add(btnExtractHighlights, 0, 2, 2, 1);

        btnExportEmail = new Button("Export to Email");
        btnExportEmail.setOnAction(actionEvent -> {
            EmailView.display();
        });
        btnExportEmail.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        deviceInfoLayout.add(btnExportEmail, 0, 3, 2, 1);

    }

    public Pane getView() {
       return deviceInfoLayout;
    }
}
