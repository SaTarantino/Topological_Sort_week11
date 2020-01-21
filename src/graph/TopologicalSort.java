package graph;

import java.util.List;

/**
 * Do a topological sort on a graph.  <b>Note:</b>  A <tt>TopologicalSort</tt> object <i>is</i> a
 * {@link Graph}, so it sorts <i>itself</i>.  There is no need to "provide" the <tt>TopologcialSort</tt> with a
 * graph.
 *
 * @param <T> the type of object in this graph.
 *
 * @author Hugh Osborne
 * @version November 2019
 */
public interface TopologicalSort<T> extends Graph<T> {
	
	/**
	 * Do a topological sort on this graph, if it is acyclic.  A topological sort is a listing of the nodes in
	 * the (acyclic) graph such that if there is a path in the graph from node A to node B then node A will appear
	 * before node B in the listing.
	 * @return a topological sort of this graph
	 * @throws GraphError if the graph is not acyclic
	 */
	public List<T> getSort() throws GraphError;

}
