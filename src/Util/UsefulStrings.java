package Util;


/**
 * A small class responsible for various String operations.
 */
public class UsefulStrings {


    private static final String TAB = "    ";
    private static final String NEWLINE = "\n";

    public static String getTab() {
        return  TAB;
    }

    public static String getNL() {
        return NEWLINE;
    }

    public static String addTabsForMultipleLineString(String input) {
        String[] lines = input.split(System.getProperty("line.separator"));
        String result = "";
        for(String s : lines) {
            result += getTab() + s + getNL();
        }
        return result;
    }
}
