package it.unibs.fp.the_trinity.utilities;

public class NiceStrings {

    private final static String SPACE = " ";
    private final static String FRAME = "---------------------------------------------------";
    private final static String ESCAPE = "\n";


    public static String frame(String s) {
        StringBuffer res = new StringBuffer();

        res.append(FRAME + ESCAPE);
        res.append(s + ESCAPE);
        res.append(FRAME + ESCAPE);

        return res.toString();

    }


    public static String incolumn(String s, int width) {
        StringBuffer res = new StringBuffer(width);
        int charValueWeHaveToPrint = Math.min(width, s.length());
        res.append(s, 0, charValueWeHaveToPrint);
        for (int i = s.length() + 1; i <= width; i++)
            res.append(SPACE);
        return res.toString();
    }

    public static String centered(String s, int width) {
        StringBuffer res = new StringBuffer(width);
        if (width <= s.length())
            res.append(s.substring(width));
        else {
            int previousSpaces = (width - s.length()) / 2;
            int nextSpaces = width - previousSpaces - s.length();
            for (int i = 1; i <= previousSpaces; i++)
                res.append(SPACE);

            res.append(s);

            for (int i = 1; i <= nextSpaces; i++)
                res.append(SPACE);
        }
        return res.toString();

    }

    public static String repeatChar(char element, int width) {
        StringBuffer result = new StringBuffer(width);
        for (int i = 0; i < width; i++) {
            result.append(element);
        }
        return result.toString();

    }

    public static String isolatedString(String weHaveToIsolate) {
        StringBuffer result = new StringBuffer();
        result.append(ESCAPE);
        result.append(weHaveToIsolate);
        result.append(ESCAPE);
        return result.toString();
    }

}

