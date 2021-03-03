package org.brplatinum.model;

import org.brplatinum.helper.TextHelper;

import java.sql.Timestamp;
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
        note = "";
        locationStart = -1;
        locationEnd = -1;
        date = null;
    }

    public Highlight(String text, Timestamp date) { //Kobo highlights do not have locations
        this.text = text;
        note = "";
        locationStart = -1;
        locationEnd = -1;
        this.date = date.toLocalDateTime().atZone(TimeZone.getDefault().toZoneId());
    }

    public Highlight(String text, int locationStart, int locationEnd, String date) {
        this.text = text;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy HH:mm:ss"));
        this.date = localDateTime.atZone(TimeZone.getDefault().toZoneId());
        ;
        note = "";
    }

    public Highlight(Highlight newHighlight) {
        text = newHighlight.getText();
        locationStart = newHighlight.getLocationStart();
        locationEnd = newHighlight.getLocationEnd();
        date = newHighlight.getDate();
        note = newHighlight.getNote();
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

    public String getNote() {
        return note;
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
        if (locationStart != -1) {
            output += "," + TextHelper.csvFix(locationStart);
        } else {
            output += ",";
        }
        output += "," + TextHelper.csvFix(date.withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        output += ",";
        output += TextHelper.csvFix(note);
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
