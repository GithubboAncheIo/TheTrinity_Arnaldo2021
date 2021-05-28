package it.unibs.fp.the_trinity.utilities;

/**
 * {@code MenuItem} represent a required class for
 * {@code Menu} class.
 *
 * @author Baresi Marco
 * @see Menu
 */
public class MenuItem {
    private String text;
    private Runnable function;

    /**
     * {@code MenuItem} constructor.
     *
     * @param text of the option
     * @param function associated to the item
     */
    public MenuItem(String text, Runnable function) {
        this.text = text;
        this.function = function;
    }

    /**
     * @return {@link #text}
     */
    public String getText() {
        return text;
    }

    /**
     * @return {@link #function}
     */
    public Runnable getFunction() {
        return function;
    }
}
