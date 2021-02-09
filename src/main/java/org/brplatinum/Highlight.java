package org.brplatinum;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Highlight {
    private String text;
    private String note;
    private int locationStart;
    private int locationEnd;
    private ZonedDateTime date;

    public Highlight() {
        text = null;
        note = null;
        locationStart = -1;
        locationEnd = -1;
        date = null;
    }

    public Highlight(String text, int locationStart, int locationEnd, String date) {
        this.text = text;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy HH:mm:ss"));
        this.date = localDateTime.atZone(TimeZone.getDefault().toZoneId());;
        note = null;
    }

    public Highlight(Highlight newHighlight) {
        text = newHighlight.getText();
        locationStart = newHighlight.getLocationStart();
        locationEnd = newHighlight.getLocationEnd();
        date = newHighlight.getDate();
        note = null;
    }

    public String getText() {
        return text;
    }

    public int getLocationStart() {
        return locationStart;
    }

    public int getLocationEnd() {
        return locationEnd;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String toCSV() {
        String output = "";
        output += "," + TextHelper.csvFix(text);
        output += "," + TextHelper.csvFix(locationStart);
        output += "," + TextHelper.csvFix(date.withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        output += ",";
        if (note != null) {
            output += TextHelper.csvFix(note);
        }

        return output;
    }

    @Override
    public String toString() {
        String output = "\tFrom location " + locationStart + " - " + locationEnd + "\n\t" + text + "\n";
        if (note != null) {
            output += "\tNote: " + note + "\n";
        }
        return output + "\n";
    }
}
