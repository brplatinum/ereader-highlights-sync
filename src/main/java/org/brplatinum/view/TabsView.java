package org.brplatinum.view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TabsView {

    VBox container;
    TabPane tabs;
    ExportToFileView exportToFileView;
    EmailView emailView;
    ReadwiseView readwiseView;

    public TabsView(Stage stage){
        container = new VBox();
        tabs = new TabPane();

        exportToFileView = new ExportToFileView(stage);
        emailView = new EmailView();
        readwiseView = new ReadwiseView();

        Tab exportToFileTab = new Tab("Export to File", exportToFileView.getView());
        Tab emailTab = new Tab("Email", emailView.getView());
        Tab readwiseTab = new Tab("Readwise", readwiseView.getView());

        tabs.getTabs().addAll(exportToFileTab, emailTab, readwiseTab);
        container.getChildren().add(tabs);
    }

    public Pane getView() {
        return container;
    }
}
