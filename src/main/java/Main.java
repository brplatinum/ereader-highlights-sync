import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    Button btnDeviceInfo;
    ComboBox cmbDeviceType;
    Device device;

    @Override
    public void start(Stage stage) {
        device = new Device();

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
        cmbDeviceType.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                device.setDeviceType((String) t1);
            }
        });

        grid.add(cmbDeviceType, 2, 0);

        Scene scene = new Scene(grid, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}