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
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    DeviceInfoView deviceInfoView;
    @Override
    public void start(Stage stage) {
        BorderPane mainLayout = new BorderPane();
        deviceInfoView = new DeviceInfoView(stage);
        Pane deviceInfoPane = deviceInfoView.getView();
        mainLayout.setTop(deviceInfoPane);


        Scene scene = new Scene(mainLayout, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}