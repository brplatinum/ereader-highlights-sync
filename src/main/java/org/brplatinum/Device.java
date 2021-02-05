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

//        highlightsStringClump = removeUnusualCharacters(highlightsStringClump);

        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(highlightsStringClump);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(filePath);
        Pattern highlightsInfo = Pattern.compile(highlightsRegex);
        Matcher matcher = highlightsInfo.matcher(highlightsStringClump);

        while(matcher.find()){

            if (matcher.group("locationName") != null){

            }
        }
    }

    private void addBook(String title, String author){
        if (books.containsKey(title+author)){

        } else {
            books.put(title+author, new Book(title, author));
        }
    }

    private String removeUnusualCharacters(String str) {
        return str.replaceAll("[\\u2018\\u2019]", "'")
                .replaceAll("[\\u201C\\u201D]", "\"")
                .replaceAll("\\u2026", "...");
    }

    private void koboExtractHighlights() {

    }

    private DeviceType deviceTypeConvert(String deviceType) {
        return switch (deviceType) {
            case "Kindle" -> DeviceType.KINDLE;
            case "Kobo" -> DeviceType.KOBO;
            default -> null;
        };
    }
}
