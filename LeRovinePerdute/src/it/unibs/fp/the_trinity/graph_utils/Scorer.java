package it.unibs.fp.the_trinity.graph_utils;

/**
 * The first part of this is some way to generate a
 * score between any two nodes. We'll the Scorer interface
 * for both the score to the next node and the estimate
 * to the destination: given a start and an end node, we
 * then get a score for traveling between them.
 *
 * @param <T> generic class.
 * @author Contestabile Martina
 * @author Baresi Marco
 */
public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);

    /**
     * This method computes the cost for the trip.
     *
     * @param array needed to compute route cost
     * @return route cost
     */
    default double computeRouteCost(T[] array) {
        double cost = 0;
        for (int i = 0; i < (array.length - 1); i++)
            cost += computeCost(array[i], array[i + 1]);
        return cost;
    }
}

