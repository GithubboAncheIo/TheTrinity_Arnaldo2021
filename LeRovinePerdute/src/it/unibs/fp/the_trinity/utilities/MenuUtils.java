package it.unibs.fp.the_trinity.utilities;

/**
 * {@code MenuUtils} contains utils for {@code Menu} class.
 *
 * @author Baresi Marco
 * @see Menu
 */
public class MenuUtils {
    public static final String ENTER = "\n↩";
    public static final String FRAME_SYMBOL = "═";
    public static final String INVALID_OPTION = "‼ Scelta ♿ ‼ (invalida)\n";
    public static final String INVALID_OPTION_A = "‼ Scelta ♿ ‼";
    public static final String INVALID_OPTION_B = " (invalida)\n";


    /**
     * Generates menu frame.
     *
     * @param frameLength length of frame
     * @return the frame in {@code String} format
     */
    public static String generateFrame(int frameLength) {
        return FRAME_SYMBOL.repeat(frameLength);
    }

    /**
     * Returns the formatted title
     */
    public static String formatTitle(String title, String frame) {
        String formattedTitle = "";
        String tempTitle = "";
        double spaces = (frame.length()-2-title.length())/2.;
        tempTitle += " ".repeat((int)Math.floor(spaces));
        tempTitle += title;
        tempTitle += " ".repeat((int)Math.ceil(spaces));
        formattedTitle += "\n╔" + frame.substring(2) + "╗";
        formattedTitle += "\n║" + tempTitle + "║";
        formattedTitle += "\n╚" + frame.substring(2) + "╝\n";
        return formattedTitle;
    }

    /**
     * Returns enters in {@code String} format
     */
    public static String getEnters(int repetitions) {
        return MenuUtils.ENTER.repeat(repetitions);
    }

    /**
     * Prints {@code text} with spelling.
     *
     * @param text to be printed
     */
    public static void spellingPrint(String text) {
        for (char s : text.toCharArray()) {
            Time.pause(Time.REALLY_LOW_MILLIS_PAUSE);
            System.out.print(s);
        }
    }
}
