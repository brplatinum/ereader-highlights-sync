package org.brplatinum;

import java.util.*;

public class Book {
    private String author;
    private String title;
    private HashMap<Integer, Highlight> highlights; //Key = highlight location end, Value = highlight

    public Book() {
        author = null;
        title = null;
        highlights = new HashMap<>() {
        };
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        highlights = new HashMap<>();
    }

    public void addHighlight(Highlight newHighlight){
        highlights.put(newHighlight.getLocationEnd(), new Highlight(newHighlight));
    }

    public void addNote(String note, int location){
        highlights.get(location).setNote(note);
    }

}
