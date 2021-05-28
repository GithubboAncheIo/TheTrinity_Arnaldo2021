package it.unibs.fp.the_trinity.objects;

import it.unibs.fp.the_trinity.graph_utils.GraphNode;

import java.util.StringJoiner;

/**
 * We also need a wrapper around our nodes that carries some extra information.
 * This is a RouteNode – because it's a node in our computed route instead of
 * one in the entire graph. As with GraphNode, these are simple Java Beans used
 * to store the current state of each node for the current route computation.
 * We've given this a simple constructor for the common case, when we're first
 * visiting a node and have no additional information about it yet
 *
 * @param <T>
 * @author Contestabile Martina
 */

class RouteNode<T extends GraphNode> implements Comparable<RouteNode<T>> {
    private final T current;
    private T previous;
    private double routeScore;
    private double estimatedScore;

    /**
     * Constructor
     *
     * @param current the current node.
     */
    RouteNode(T current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    /**
     * Constructor
     *
     * @param current        the current node.
     * @param previous       the previous node.
     * @param routeScore     the route's score between two nodes.
     * @param estimatedScore the estimated score.
     */
    RouteNode(T current, T previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    //Getters
    T getCurrent() {
        return current;
    }

    T getPrevious() {
        return previous;
    }

    double getRouteScore() {
        return routeScore;
    }

    //Setters
    void setPrevious(T previous) {
        this.previous = previous;
    }

    void setRouteScore(double routeScore) {
        this.routeScore = routeScore;
    }

    void setEstimatedScore(double estimatedScore) {
        this.estimatedScore = estimatedScore;
    }

    /**
     * These also need to be Comparable though, so that we can order
     * them by the estimated score as part of the algorithm. This means
     * the addition of a compareTo() method to fulfill the requirements
     * of the Comparable interface.
     *
     * @param other the route we are comparing to.
     * @return the shortest route.
     */
    @Override
    public int compareTo(RouteNode other) {
        return Double.compare(this.estimatedScore, other.estimatedScore);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RouteNode.class.getSimpleName() + "[", "]").add("current=" + current)
                .add("previous=" + previous).add("routeScore=" + routeScore).add("estimatedScore=" + estimatedScore)
                .toString();
    }
}