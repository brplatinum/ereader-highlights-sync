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

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
        highlights = new ArrayList<Highlight>();
    }

}
