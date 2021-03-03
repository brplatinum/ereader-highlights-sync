package org.brplatinum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.brplatinum.view.DeviceInfoView;
import org.brplatinum.view.TabsView;

public class Main extends Application {

    DeviceInfoView deviceInfoView;
    TabsView tabsView;
    @Override
    public void start(Stage stage) {
        BorderPane mainLayout = new BorderPane();

        deviceInfoView = new DeviceInfoView(stage);
        Pane deviceInfoPane = deviceInfoView.getView();
        mainLayout.setTop(deviceInfoPane);

        tabsView = new TabsView(stage);
        Pane tabsPane = tabsView.getView();
        mainLayout.setCenter(tabsPane);

        Scene scene = new Scene(mainLayout, 750, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}