package org.brplatinum;

public class TextHelper {
    //Parallel arrays for removing special characters
    private static final String[] specialCharacters = {"\u2018|\u2019", "\u201C|\u201D", "\u2026"};
    private static final String[] specialCharacterReplacements = {"'", "\"", "..."};

    public static String csvFix(String input) { //Fixes formatting for CSV and converts some special unicode characters to standard characters
        String fixedInput = input.replaceAll("\\r\\n", " ");
        fixedInput = fixedInput.replaceAll("\\r", " ");
        fixedInput = fixedInput.replaceAll("\\n", " ");
        if (fixedInput.contains(",") || fixedInput.contains("\"") || fixedInput.contains("'")) {
            fixedInput = fixedInput.replaceAll("\"", "\"\"");
            fixedInput = "\"" + fixedInput + "\"";
        }
        return fixedInput;
    }

    public static String csvFix(int input) {
        return csvFix(Integer.toString(input));
    }

    public static String removeBOM(String s) {
        if (s.startsWith("\uFEFF")) {
            s = s.substring(1);
        }
        return s;
    }

}
