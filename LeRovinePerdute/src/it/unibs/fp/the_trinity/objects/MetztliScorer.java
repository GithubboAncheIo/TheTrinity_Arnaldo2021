package it.unibs.fp.the_trinity.objects;

import it.unibs.fp.the_trinity.graph_utils.Scorer;

/**
 * {@code MetztliScorer} class computes the cost for the Metztli team.
 *
 * @author Baresi Marco
 */
public class MetztliScorer implements Scorer<City> {

    @Override
    public double computeCost(City from, City to) {
        return Math.abs(to.getHeight() - from.getHeight());
    }
}