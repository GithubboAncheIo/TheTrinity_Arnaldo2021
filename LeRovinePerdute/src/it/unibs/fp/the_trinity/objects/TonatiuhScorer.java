package it.unibs.fp.the_trinity.objects;

import it.unibs.fp.the_trinity.graph_utils.Scorer;

/**
 * {@code TonatiuhScorer} class computes the cost for the Tonatiuh team.
 *
 * @author Baresi Marco
 */
public class TonatiuhScorer implements Scorer<City> {
    private static final int SQUARE = 2;

    @Override
    public double computeCost(City from, City to) {
        return Math.sqrt(Math.pow(from.getX() - to.getX(), SQUARE) + Math.pow(from.getY() - to.getY(), SQUARE));
    }
}
