package it.unibs.fp.the_trinity.utilities;

public class Range {
    private double min;
    private double max;
    public static final int CONFLICT = -1;
    /*
     * EQUALS significa che una delle due coordinate (tra il range massimo e minimo)
     * corrisponde esattamente a max o min.
     * Mi servira' per verificare se la collisione avvione con il sole
     */
    public static final int EQUALS = 0;

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean contains(double number) {
        return (number > min && number < max);
    }

    public boolean equalsMinOrMax(double number) {
        return (number == min || number == max);
    }

    public boolean containsRange(Range range) {
        return contains(range.min) || contains(range.max);
    }

    public int compare(Range range) {
        if (contains(range.min) || contains(range.max) || range.containsRange(this))
            return CONFLICT;
        if (equalsMinOrMax(range.min) || equalsMinOrMax(range.max))
            return EQUALS;
        return  range.min < min ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Range{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}