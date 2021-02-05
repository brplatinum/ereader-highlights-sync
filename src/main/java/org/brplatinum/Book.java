package org.brplatinum;

import java.util.*;

public class Book {
    private String author;
    private String title;
    private List<Highlight> highlights;

    public Book() {
        author = null;
        title = null;
        highlights = new ArrayList<Highlight>();
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        highlights = new ArrayList<Highlight>();
    }

    public void addHighlight(String highlightText){
        highlights.add(new Highlight());
    }

}
