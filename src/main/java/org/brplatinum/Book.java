package org.brplatinum;

import java.util.*;

public class Book {
    private String author;
    private String title;
    private TreeMap<Integer, Highlight> highlights; //Key = highlight location end, Value = highlight
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

    public void addHighlight(Highlight newHighlight){
        highlights.put(newHighlight.getLocationEnd(), new Highlight(newHighlight));
    }

    public void addNote(String note, int location){
        highlights.get(location).setNote(note);
    }

    @Override
    public String toString(){
        String output = title + " (" + author + ")\n";
        for(int currentKey : highlights.keySet()){
            output += highlights.get(currentKey).toString();
        }
        return output;
    }

}
