package org.brplatinum.model;

import java.time.ZonedDateTime;
import java.util.*;

public class Book {
    private String author;
    private String title;
    private TreeMap<ZonedDateTime, Highlight> highlights; //Key = highlight location end, Value = highlight
    //Sorted by location end, ascending order

    public Book() {
        author = null;
        title = null;
        highlights = new TreeMap<>() {
        };
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        highlights = new TreeMap<>();
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void addHighlight(Highlight newHighlight) {
        highlights.put(newHighlight.getDate(), new Highlight(newHighlight));
    }

    public void addNote(String note, int location) {
        highlights.get(location).setNote(note);
    }

    public String[] highlightsToCSV() {
        String[] export = new String[highlights.size()];
        int i = 0;
        for (ZonedDateTime currentKey : highlights.keySet()) {
            export[i] = highlights.get(currentKey).toCSV();
            i++;
        }
        return export;
    }

    @Override
    public String toString() {
        String output = title + " (" + author + ")\n";
        for (ZonedDateTime currentKey : highlights.keySet()) {
            output += highlights.get(currentKey).toString();
        }
        return output;
    }

}
