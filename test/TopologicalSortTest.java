import graph.GraphError;
import graph.ReferenceCounting;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    ReferenceCounting<Integer> graph = new ReferenceCounting();

    void generateGraph() throws GraphError {
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
    }

    @Test
    void containTest() throws GraphError {
        generateGraph();
        assertTrue(graph.contains(0));
        assertTrue(graph.contains(1));
        assertTrue(graph.contains(2));
        assertTrue(graph.contains(3));
        assertTrue(graph.contains(4));
    }

    @Test
    void graphSize() throws GraphError {
        generateGraph();
        List<Integer> graphSize = graph.getSort();
        assertEquals(graphSize.size(), 5);
    }

    /**
     * In most of the case checking for a topological sort is not possible until that
     * there isn't only one right way to do it. In my case java follow always the same pattern
     * so we can check if the topological method is working properly.
     */
    @Test
    void topologicalSortTest() throws GraphError {
        generateGraph();
        List<Integer> graphSize = graph.getSort();
        List<Integer> topologicalList = Arrays.asList(0, 3, 4, 1, 2);
        assertEquals(graphSize, topologicalList);
    }
}
