package org.brplatinum;

public class Highlight {
    private String text;
    private String note;
    private int locationStart;
    private int locationEnd;

    public Highlight(){
        text = null;
        note = null;
        locationStart = -1;
        locationEnd = -1;
    }
    public Highlight(String text, int locationStart, int locationEnd){
        this.text = text;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
    }

    public Highlight(Highlight newHighlight){
        text = newHighlight.getText();
        locationStart = newHighlight.getLocationStart();
        locationEnd = newHighlight.getLocationEnd();
    }

    public String getText(){
        return text;
    }

    public int getLocationStart(){
        return locationStart;
    }

    public int getLocationEnd(){
        return locationEnd;
    }

}
