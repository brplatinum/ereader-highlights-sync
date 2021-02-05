package org.brplatinum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device {
    private DeviceType deviceType;
    private String path;
    private HashMap<String, Book> books;

    public Device() {
        deviceType = null;
        path = null;
        books = new HashMap<String, Book>();
    }

    public void setDeviceType(String deviceTypeInput) {
        deviceType = deviceTypeConvert(deviceTypeInput);
    }

    public void setPath(String pathInput) {
        path = pathInput;
        System.out.println(path);
    }

    public void extractHighlights() {
        switch (deviceType) {
            case KINDLE:
                kindleExtractHighlights();
                kindleExtractNotes();
            case KOBO:
                koboExtractHighlights();
        }
    }

    private void kindleExtractHighlights() {
        String highlightsRegex = "(?<title>.+) \\((?<author>.+)\\)\r\n- ((Your Highlight on page (?<page>[0-9]+) \\| location (?<locationStart>[0-9]+)-(?<locationEnd>[0-9]+))|(Your Highlight at location (?<altLocationStart>[0-9]+)-(?<altLocationEnd>[0-9]+))) \\| Added on (?<date>.+)\r\n\r\n(?<highlight>.+)\r\n==========";
        String separator = System.getProperty("file.separator");
        Path filePath = Paths.get(path + "documents" + separator + "My Clippings.txt" + separator);
        System.out.println("exists?" + new File(path + "documents" + separator + "My Clippings.txt" + separator).exists());
        String highlightsStringClump = "";

        try {
            highlightsStringClump = Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.print(e.toString());
        }

        Pattern highlightsInfo = Pattern.compile(highlightsRegex);
        Matcher matcher = highlightsInfo.matcher(highlightsStringClump);

        while (matcher.find()) {
            Highlight newHighlight;
            if (matcher.group("locationName") != null) {
                newHighlight = new Highlight(matcher.group("highlight"), Integer.valueOf(matcher.group("locationStart")), Integer.valueOf(matcher.group("locationEnd")));
            } else {
                newHighlight = new Highlight(matcher.group("highlight"), Integer.valueOf(matcher.group("altLocationStart")), Integer.valueOf(matcher.group("altLocationEnd")));

            }
            addHighlightToBook(matcher.group("title"), matcher.group("author"), newHighlight);
        }
    }

    private void kindleExtractNotes() {
        String notesRegex = "(?<title>.+) \\((?<author>.+)\\)\r\n- Your Note at location (?<location>[0-9]+) \\| Added on (?<date>.+)\r\n\r\n(?<note>.+)\r\n==========";
        String separator = System.getProperty("file.separator");
        Path filePath = Paths.get(path + "documents" + separator + "My Clippings.txt" + separator);
        System.out.println("exists?" + new File(path + "documents" + separator + "My Clippings.txt" + separator).exists());
        String notesClump = "";

        try {
            notesClump = Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.print(e.toString());
        }

        Pattern notesInfo = Pattern.compile(notesRegex);
        Matcher matcher = notesInfo.matcher(notesClump);

        while (matcher.find()) {
            addNoteToHighlight(matcher.group("title"), matcher.group("author"), matcher.group("note"), Integer.parseInt(matcher.group("location")));
        }
    }

    private void addHighlightToBook(String title, String author, Highlight newHighlight) {
        if (!books.containsKey(title + author)) {
            books.put(title + author, new Book(title, author)); //Creates a new, no-highlight book and adds to the HashMap
        }
        books.get(title + author).addHighlight(newHighlight);
    }

    private void addNoteToHighlight(String title, String author, String note, int location) {
        if (books.containsKey(title + author)) {
            books.get(title + author).addNote(note, location); //Creates a new, no-highlight book and adds to the HashMap
        }
    }

    private String removeUnusualCharacters(String str) {
        return str.replaceAll("[\\u2018\\u2019]", "'")
                .replaceAll("[\\u201C\\u201D]", "\"")
                .replaceAll("\\u2026", "...");
    }

    private void koboExtractHighlights() {

    }

    private DeviceType deviceTypeConvert(String deviceType) { //Maps the string name of the device type to the enum
        return switch (deviceType) {
            case "Kindle" -> DeviceType.KINDLE;
            case "Kobo" -> DeviceType.KOBO;
            default -> null;
        };
    }
}
