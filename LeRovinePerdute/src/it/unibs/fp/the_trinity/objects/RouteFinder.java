package it.unibs.fp.the_trinity.objects;


import it.unibs.fp.the_trinity.graph_utils.Graph;
import it.unibs.fp.the_trinity.graph_utils.GraphNode;
import it.unibs.fp.the_trinity.graph_utils.Scorer;

import java.util.*;

/**
 * @param <T>
 * @author Contestabile Martina
 * @author Baresi Marco
 */
public class RouteFinder<T extends GraphNode> {
    private final Graph<T> graph;
    private final Scorer<T> nextNodeScorer;
    private final Scorer<T> targetScorer;

    /**
     * Constructor.
     *
     * @param graph          the world's map
     * @param nextNodeScorer represent the way that we use to compute scorer between nodes
     * @param targetScorer   represent the way that we use to compute scorer between one node
     *                       and the destination
     */
    public RouteFinder(Graph<T> graph, Scorer<T> nextNodeScorer, Scorer<T> targetScorer) {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetScorer = targetScorer;
    }

    /**
     * It computes the route.
     *
     * @param from the departure node.
     * @param to   the arrival node.
     * @return the route from the departure to the arrival.
     */
    public List<T> findRoute(T from, T to) {
        Map<T, RouteNode<T>> allNodes = new HashMap<>();
        Queue<RouteNode<T>> openSet = new PriorityQueue<>();

        RouteNode<T> start = new RouteNode<>(from, null, 0d, targetScorer.computeCost(from, to));
        allNodes.put(from, start);
        openSet.add(start);


        while (!openSet.isEmpty()) {
            //System.out.println("Open Set contains: " + openSet.stream().map(RouteNode::getCurrent).collect(Collectors.toSet()));
            RouteNode<T> next = openSet.poll();
            //System.out.println("Looking at node: " + next.getCurrent().getId());
            if (next.getCurrent().equals(to)) {
                //System.out.println("Found our destination!");

                List<T> route = new ArrayList<>();
                RouteNode<T> current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);

                //System.out.println("Route: " + route);

                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection -> {
                double newScore = next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(), connection);
                RouteNode<T> nextNode = allNodes.getOrDefault(connection, new RouteNode<>(connection));
                allNodes.put(connection, nextNode);

                if (nextNode.getRouteScore() > newScore) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetScorer.computeCost(connection, to));
                    openSet.add(nextNode);
                }
            });

        }

        throw new IllegalStateException("No route found");
    }

}