package org.brplatinum;

public class CSVHelper {
    public static String csvFix(String input) { //Fixes formatting for CSV and converts some special unicode characters to standard characters
        String fixedInput = input.replaceAll("\\r\\n", " ");
        fixedInput = fixedInput.replaceAll("\\r", " ");
        fixedInput = fixedInput.replaceAll("\\n", " ");
        fixedInput = fixedInput.replaceAll("\u2018|\u2019", "'");
        fixedInput = fixedInput.replaceAll("\u201C|\u201D", "\"");
        fixedInput = fixedInput.replaceAll("\u2026", "...");
        if (fixedInput.contains(",") || fixedInput.contains("\"") || fixedInput.contains("'")) {
            fixedInput = fixedInput.replaceAll("\"", "\"\"");
            fixedInput = "\"" + fixedInput + "\"";
        }
        return fixedInput;
    }

    public static String csvFix(int input) {
        return csvFix(Integer.toString(input));
    }

}
