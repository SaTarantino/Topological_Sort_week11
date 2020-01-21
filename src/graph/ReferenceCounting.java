package graph;

import java.util.*;

public class ReferenceCounting<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {

    private HashMap<T, Integer> referencesCount = new HashMap<T, Integer>();
    private List<T> sort = new ArrayList<T>();

    @Override
    public List<T> getSort() throws GraphError {

        setUpReferenceCounts();
        doSort();
        if (sort.size() != getNodes().size()) {
            throw new GraphError();
        }
        return sort;
    }

    /**
     * Reference count method
     * @throws GraphError
     */
    private void setUpReferenceCounts() throws GraphError {

        for (T node: getNodes()) {
            referencesCount.put(node, 0);
        }

        for (T node : getNodes()) {
            for (T neighbour : getNeighbours(node)) {
                referencesCount.put(neighbour, referencesCount.get(neighbour) + 1);
            }
        }
    }

    /**
     * Method for take count of the visited nodes
     * @param node
     * @throws GraphError
     */
    private void visitNode(T node) throws GraphError {

        sort.add(node);
        for (T neighbour : getNeighbours(node)) {
            referencesCount.put(neighbour, referencesCount.get(neighbour) - 1);
        }
        referencesCount.remove(node);
    }

    private void doSort() throws GraphError {
        T node;
        while ((node = nextReferenceZeroNode()) != null) {
            visitNode(node);
        }
    }

    private T nextReferenceZeroNode() {

        for (Map.Entry<T, Integer> entry : referencesCount.entrySet()) {
            if (entry.getValue() == 0) {
                return (T) entry.getKey();
            }
        }
        return null;
    }

    public static void main(String[] args) throws GraphError {
        ReferenceCounting<Integer> graph= new ReferenceCounting<Integer>();
        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);

        graph.add(0, 1);
        graph.add(0, 3);
        graph.add(1, 2);
        graph.add(3, 1);
        graph.add(4, 1);

        graph.getSort();
        System.out.println("Topological Sort: "+Arrays.toString((graph.sort.toArray())));
    }
}