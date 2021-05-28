package it.unibs.fp.the_trinity.tamagolem;

import java.util.*;

public class FightUtils {
    public static final int EASY_LEVEL = 1;
    public static final int NORMAL_LEVEL = 2;
    public static final int HARD_LEVEL = 3;
    public static final int MIN_ELEMENTS = 3;
    public static final int MAX_EASY_LEVEL = 5;
    public static final int MIN_NORMAL_LEVEL = 6;
    public static final int MAX_NORMAL_LEVEL = 8;
    public static final int MIN_HARD_LEVEL = 9;
    public static final int MAX_ELEMENTS = 10;
    public static final int ENERGY = 10;

    /**
     * This method returns an {@code int} type that rapresents the
     * element's number used in the fight.
     * @param level the match's level.
     * @return the number of elements, that is an {@code int}.
     */
    public static int howManyElements(int level) {
        int min = 0;
        int max = 0;

        switch (level) {
            case FightUtils.EASY_LEVEL -> {
                min = FightUtils.MIN_ELEMENTS;
                max = FightUtils.MAX_EASY_LEVEL;
            }

            case FightUtils.NORMAL_LEVEL -> {
                min = FightUtils.MIN_NORMAL_LEVEL;
                max = FightUtils.MAX_NORMAL_LEVEL;
            }

            case FightUtils.HARD_LEVEL -> {
                min = FightUtils.MIN_HARD_LEVEL;
                max = FightUtils.MAX_ELEMENTS;
            }
        }
        return  (int) Math.floor(Math.random() * (max - min +1) + min);
    }

    /**
     * This method allows the most equilibrate fight we can aim, because, if it is called, it allows the random generation of the elements, so that
     * 1.Every time the players decide to start a new fight, the elements and the equilibrium is always different.
     * 2.Every fight is different from the others.
     * @param elementsToExtract number of elements to extract
     * @return elements that are all the elements involved in the current fight.
     */
    public static ArrayList<TamaElement> generateRandomElements(int elementsToExtract) {
        ArrayList<TamaElement> elements = new ArrayList<>();
        List<TamaElement> elem = Arrays.asList(TamaElement.values());
        Collections.shuffle(elem);
        for (int i=0; i<elementsToExtract; i++) elements.add(elem.get(i));
        return elements;
    }

    /**
     * This method calculates the stones involved in the match.
     *
     * @param elements are the elements involved in the match.
     * @return how many stones are involved in the match.
     */
    public static int howManyStones(int elements) {
        return (int) (Math.ceil((elements + 1.0) / 3.0) + 1.0);
    }

    /**
     * This method calculates the tamagolems involved in the match.
     * This integer value is based on the elements.
     *
     * @param elements are the elements involved in the match.
     * @param stones   how many stones are involved in the match.
     * @return the tamagolems' number.
     */
    public static int howManyTamagolems(int elements, int stones) {
        return (int) (Math.ceil(((elements - 1.0) * (elements - 2.0)) / (2.0 * stones)));
    }

    /**
     * This method calculates the common stones involved in the match.
     *
     * @param tamagolems the tamagolems' number.
     * @param elements   are the elements involved in the match.
     * @param stones     how many stones are involved in the match.
     * @return the common stones.
     */
    public static int howManyCommonStones(int tamagolems, int elements, int stones) {
        return (int) (Math.ceil(((2.0 * tamagolems * stones) / elements)) * elements);
    }

    /**
     * This method calculates the common stones for each element involved in the match.
     *
     * @param tamagolems the tamagolems' number.
     * @param elements   are the elements involved in the match.
     * @param stones     how many stones are involved in the match.
     * @return the stones for each element.
     */
    public static int howManyStonesForEachElement(int tamagolems, int elements, int stones) {
        return (int) (Math.ceil((2.0 * tamagolems * stones) / elements));
    }

    /**
     * This method sets for each stone, that is represented by a single element, the number of available stones for the player in the entire fight.
     * @param usedElements generated elements of match
     * @param stonesForEachElement number of stones for each element
     * @return numberOfElementAndStones.
     */
    public static HashMap<TamaElement, Integer> generateElementAndStonesMap(ArrayList<TamaElement> usedElements, int stonesForEachElement) {
        HashMap<TamaElement, Integer> numberOfElementAndStones = new HashMap<>();
        for (TamaElement e : usedElements)
            numberOfElementAndStones.put(e, stonesForEachElement);
        return numberOfElementAndStones;
    }
}
