package it.unibs.fp.the_trinity.utilities;

import java.util.TreeMap;

/**
 * Customizable console {@code Menu}.
 *
 * @author Baresi Marco
 */
public class Menu implements Runnable {
    private String title;
    private TreeMap<String, MenuItem> itemsMap;
    private String endKey = "0";
    private int frameLength;
    private int enterRepetitions = 10;
    private String exit = "EXIT";

    /**
     * Constructs a new {@code Menu} with input {@code Items}.
     *
     * @param items the menu's items
     */
    public Menu(String title, MenuItem[] items) {
        this.title = title;
        itemsMap = new TreeMap<>();
        for (int i=0; i<items.length; i++) {
            itemsMap.put((i+1)+"", items[i]);
        }
        frameLength = 0;
    }

    /**
     * Starts the execution of {@code Menu}.
     */
    @Override
    public void run() {
        generateFrameLength();
        String frame = MenuUtils.generateFrame(frameLength);

        String choice;
        do {
            System.out.println(MenuUtils.formatTitle(title, frame));
            printOptions();
            System.out.println(frame);
            choice = DataInput.readNotEmptyString("» ");
            if (itemsMap.containsKey(choice))
                itemsMap.get(choice).getFunction().run();
            else if (!choice.equals(endKey)) {

                System.out.print(MenuUtils.INVALID_OPTION_A);
                Time.pause(Time.LOW_MILLIS_PAUSE);
                MenuUtils.spellingPrint(MenuUtils.INVALID_OPTION_B);
                Time.pause(Time.LOW_MILLIS_PAUSE);

            }
            System.out.println(MenuUtils.getEnters(enterRepetitions));
        } while (!choice.equals(endKey));
    }

    /**
     * Prints {@link #itemsMap} values that represent options menu.
     */
    private void printOptions() {
        for (String key : itemsMap.keySet()) {
            System.out.println("[" + key + "] " + itemsMap.get(key).getText());
        }
        System.out.println("[" + endKey + "] " + exit + "\n");
    }

    /**
     * This method allows to change a keyValue value. Return {@code true} if {@link #itemsMap}
     * does not contain a key with the same value and the key will be replaced.
     *
     * @param oldKey old key value to replace
     * @param newKey new key value
     * @return {@code true} if endKey was replaced;
     *         {@code false} otherwise
     */
    public boolean changeChoiceKey(String oldKey, String newKey) {
        if (!itemsMap.containsKey(oldKey) || itemsMap.containsKey(newKey) || newKey.equals(endKey))
            return false;
        itemsMap.put(newKey, itemsMap.remove(oldKey));
        return true;
    }

    /**
     * This method allows to change {@link #endKey} value. Return {@code true} if {@link #itemsMap}
     * does not contain a key with the same value.
     *
     * @param newEndKey new endKey value
     * @return {@code true} if endKey was replaced;
     *         {@code false} otherwise
     */
    public boolean changeEndKey(String newEndKey) {
        if (itemsMap.containsKey(newEndKey))
            return false;
        endKey = newEndKey;
        return true;
    }

    /**
     * This method allows to change {@link #enterRepetitions} value.
     *
     * @param enterRepetitions new {@link #enterRepetitions} number
     * @return {@code true} if enterRepetitions was replaced;
     *         {@code false} otherwise
     */
    public boolean changeEnterRepetitions(int enterRepetitions) {
        if (enterRepetitions < 0) return false;
        this.enterRepetitions = enterRepetitions;
        return true;
    }

    /**
     * This method allows to change {@link #enterRepetitions} value.
     *
     * @param text new {@link #exit} text
     */
    public void changeExitText(String text) {
        exit = text;
        generateFrameLength();
    }

    /**
     * This method generates {@link #frameLength}.
     */
    private void generateFrameLength() {
        int t;
        for (String key : itemsMap.keySet()) {
            t = (itemsMap.get(key).getText().length() + 4);
            if (t > frameLength)
                frameLength = t;
        }
        if (title.length()+4 > frameLength)
            frameLength = title.length()+4;

        if (exit.length()+3 > frameLength)
            frameLength = exit.length()+3;
    };
}
