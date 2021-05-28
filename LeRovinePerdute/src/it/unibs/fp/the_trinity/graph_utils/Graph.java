package it.unibs.fp.the_trinity.graph_utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class lets us to stores all of
 * the nodes in our graph and has knowledge
 * of which nodes connect to which. We can
 * then get any node by ID, or all of the
 * nodes connected to a given node.
 *
 * @param <T> the generic class.
 * @author Baresi Marco
 * @author Contestabile Martina
 */

public class Graph<T extends GraphNode> {
    private final HashMap<String, T> nodes;
    private final Map<String, Set<String>> connections;

    /**
     * Constructor.
     *
     * @param nodes       the nodes.
     * @param connections the connections among the nodes.
     */
    public Graph(HashMap<String, T> nodes, Map<String, Set<String>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    /**
     * Returns the city associated to the city's ID.
     *
     * @param id the city's ID.
     * @return the city's name.
     */
    public T getNode(String id) {
        return nodes.get(id);
    }

    /**
     * Return all nodes connected to {@code node}.
     *
     * @param node to be checked
     * @return all nodes connected to {@code node}.
     */
    public Set<T> getConnections(T node) {
        return connections.get(node.getId()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }
}

