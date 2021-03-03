package org.brplatinum.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.brplatinum.model.FileType;

import java.io.File;

public class ExportToFileView {

    GridPane exportToFileLayout;

    Label lblFileType;
    ComboBox cmbFileType;
    Button btnExport;


    public ExportToFileView(Stage stage) {
        exportToFileLayout = new GridPane();

        lblFileType = new Label("File Type:");
        exportToFileLayout.add(lblFileType, 0, 0, 1, 1);
        lblFileType.setMinHeight(50);
        lblFileType.setMinWidth(100);

        ObservableList<String> fileTypeOptions = FXCollections.observableArrayList(
                "CSV",
                "TXT",
                "MD",
                "DOCX",
                "Notion",
                "Evernote",
                "OneNote"
        );
        cmbFileType = new ComboBox();
        cmbFileType.setItems(fileTypeOptions);
        cmbFileType.getSelectionModel().selectFirst();
        cmbFileType.setMinHeight(50);
        cmbFileType.setMinWidth(100);
        GridPane.setHgrow(cmbFileType, Priority.ALWAYS);
        exportToFileLayout.add(cmbFileType, 1, 0, 1, 1);

        btnExport = new Button("Export");
        btnExport.setMinHeight(50);
        btnExport.setMinWidth(100);
        GridPane.setHgrow(btnExport, Priority.ALWAYS);

        btnExport.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export Highlights");
            fileChooser.setInitialFileName("highlights");

            switch (FileType.valueOf(cmbFileType.getValue().toString().toUpperCase())) {
                case CSV:
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Comma-separated Values File", "*.csv"));
                case TXT:
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
                case MD:
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Markdown File", "*.md"));
                case DOCX:
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word File", "*.docx"));
                case NOTION:
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Notion Markdown File", "*.md"));
                case EVERNOTE:
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Evernote File", "*.enex"));
                case ONENOTE:
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("OneNote File", "*.one"));
            }

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                String filePath = file.toString();
            }
        });

        exportToFileLayout.add(btnExport, 0, 1, 2, 1);

    }

    public Pane getView() {
        return exportToFileLayout;
    }
}
