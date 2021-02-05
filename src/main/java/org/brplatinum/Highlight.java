package org.brplatinum;

public class Highlight implements Comparable<Highlight>{
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

        note = null;
    }

    public Highlight(Highlight newHighlight){
        text = newHighlight.getText();
        locationStart = newHighlight.getLocationStart();
        locationEnd = newHighlight.getLocationEnd();

        note = null;
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

    public void setNote(String noteInput) {
        note = noteInput;
    }

    @Override
    public int compareTo(Highlight otherHighlight) {
        return locationEnd - otherHighlight.getLocationEnd();
    }
}
