package TP6;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/** This class implements a graph as an adjacency-list.<br>
 *  The graph may be oriented or not and may store any information
 *  on nodes and on edges using (key,value) principle where key is a String 
 *  and value an Object.
 *  Nodes N are retrieved using their <i>hashcode</i>.
 *  <br> Use {@code GraphTest} class for testing it.
 *  <br> Use {@code GraphIO} class for converting a graph into an array, an image, to be displayed in a Graphics...
 *  <br> Use {@code GraphAlgorithms} class for using algorithms on this graph structure like shortestPath.
 * 
 * @author Dr. Denis Pallez<br>
 * <a href="http://denispallez.i3s.unice.fr">http://denispallez.i3s.unice.fr</a>
 * 
 * @param <N> N represents the types of <i>N</i>odes. 
 */
public class Graph<N> {
	// all information about a node is stored in nodeInfo inner class
	// using (String, Object) principle.
	// orientation of the graph
	private boolean orientedGraph ;
	// Integer represents the hashcode of node of the graph.
	private LinkedHashMap<Integer, nodeInfo<N>> ladj ;
	
	// make disable constructor by default
	private Graph() {}
	
	/**
	 * Build an oriented or unoriented graph
	 * @param oriented Orientation of all edges of the graph
	 */
	public Graph(boolean oriented) {
		ladj = new LinkedHashMap<Integer, nodeInfo<N>>() ;
		orientedGraph = oriented ;
	}
	
	/** Build a graph from another graph {@code g}.<br>
	 * Make a deep copy of any information contained
	 * in the graph.
	 * 
	 * @param g the graph
	 */
	public Graph(Graph<N> g) {
		orientedGraph=g.orientedGraph ;
		for (Integer code1:g.ladj.keySet() ) {
			N node1 = g.getNode(code1) ; 
			addNode(node1) ;
			// add all values on node 
			for (String key:g.ladj.get(code1).getAttributesOnNode().keySet()) {
				addAttributeOnNode(node1, key, g.getAttributeOnNode(node1, key)) ;
			}
			for (Integer code2:g.ladj.get(code1).edges.keySet()) {
				// add all values contained on edge node1->node2
				N node2 = g.getNode(code2) ;
				for (String key:g.ladj.get(code1).getAttributesOnEdge(code2).keySet()) {
					addAttributeOnEdge(node1, node2, key,g.ladj.get(code1).getAttributeOnEdge(code2, key)) ;
				}
			}
		}
	}
	
	/** Are edges oriented or not ?
	 * 
	 * @return a boolean representing whether all edges of the graph are oriented or not 
	 */
	public boolean isOriented() {
		return orientedGraph;
	}
	
	/** 
	 * Get a number that should be unique in the graph.
	 * Method is based on hashcode of {@code node}.
	 * @param node
	 * @return a unique number of the node in the graph
	 */
	private Integer getCode(N node) {
		return node.hashCode() ;
	}
	
	
	/** 
	 * Retrieve node in the graph using its {@code code}.<br>
	 * @param code Integer representing the hashcode of the object.
	 * @return an object of type N used when adding the node in the graph. 
	 */
	private N getNode(Integer code) {
		if (ladj.containsKey(code)) {
			return ladj.get(code).getNode() ;
		}
		else return null ;
	}
	
	/** Is node {@code node} stored in the graph?
	 */
	public boolean isNode(N node) {
		return ladj.containsKey(getCode(node)) ;
	}

	/** Add node {@code node} in the graph.
	 * Use the hashcode of node as a key.
	 * @param node node to add in the graph.
	 */
	public void addNode(N node) {
		if (!isNode(node)) {
			ladj.put(getCode(node), new nodeInfo<N>(node));
		}
	}
	
	/** Add information on node {@code node}.
	 * 
	 * @param node where information is stored
	 * @param key a String value for retrieving the information
	 * @param value information to store on the node associated to key
	 */
	public void addAttributeOnNode(N node, String key, Object value) {
		Integer code = getCode(node) ; 
		addNode(node) ;
		ladj.get(code).addAttributeOnNode(key,value) ;
	}
	
	/** Retrieve information called {@code key} on node {@code node}.
	 * @param node the node where information is stored
	 * @param key the key information to retrieve
	 */
	public Object getAttributeOnNode(N node, String key) {
		return ladj.get(getCode(node)).getAttributeOnNode(key) ;
	}
	
	public HashMap<String,Object> getAttributesOnNode(N node) {
		return ladj.get(getCode(node)).properties ;
	}
	
	/** Add an edge between nodes {@code node1} and {@code node2} 
	 * with an information called {@code key}
	 * and represented by {@code value}.<br> 
	 * If {@code node1} or {@code node2} are not in the graph, 
	 * nodes are automatically added.<br>
	 * If graph is not oriented, add automatically an edge between {@code node2} and {@code node1}. 
	 */
	public void addAttributeOnEdge(N node1, N node2, String key, Object value) {
		if (!isNode(node1)) {
			addNode(node1) ;
		}
		if (!isNode(node2)) {
			addNode(node2) ;
		}
		ladj.get(getCode(node1)).addAttributeOnEdge(getCode(node2),key,value) ;
		if (!orientedGraph) {
			ladj.get(getCode(node2)).addAttributeOnEdge(getCode(node1),key,value) ;
		}
	}
	
	/** Retrieve information called {@code key} on edge 
	 * between nodes {@code node1} and {@code node2}. <br>
	 * If edge does not exist, return null.
	 * @param node1
	 * @param node2
	 * @param key
	 */
	public Object getAttributeOnEdge(N node1, N node2, String key) {
		if (isNode(node1) && isNode(node2)) {
			return ladj.get(getCode(node1)).getAttributeOnEdge(getCode(node2),key) ;
		}
		else return null ;
	}
	
	public HashMap<String, Object> getAttributesOnEdge(N node1, N node2) {
		if (isNode(node1) && isNode(node2)) {
			return ladj.get(getCode(node1)).getAttributesOnEdge(getCode(node2)) ;
		}
		else return null ;
	}
	
	/** Remove edge between nodes {@code node1} and {@code node2}. <br>
	 * If graph is not oriented edge between nodes {@code node2} and {@code node1}
	 * is also deleted.<br>
	 * @param node1
	 * @param node2
	 */
	public void deleteEdge(N node1, N node2) {
		Integer code1 = getCode(node1) ;
		Integer code2 = getCode(node2) ;
		if (isNode(node1) && isNode(node2)) {
			// remove edge node1->node2 
			ladj.get(code1).clearAttributesOnEdge(code2); 
			ladj.get(code1).edges.remove(code2) ;
			if (!orientedGraph) {
				// remove edge node2->node1
				ladj.get(code2).clearAttributesOnEdge(code1); ladj.get(code1).edges.remove(code1) ;
			}
		}
	}
	
	/** Remove node {@code node} and all incident edges.
	 * @param node
	 */
	public void deleteNode(N node) {
		Integer code = getCode(node) ;
		if (ladj.containsKey(code)) {			
			// delete node in succ list of any node of the graph
			for (Integer anynode:ladj.keySet()) {
				ladj.get(anynode).removeEdge(code) ;
			}
			// delete the node
			ladj.remove(code) ;
		}
	}
	
	/** Remove information called {@code key} on all nodes and all edges of the graph.
	 */
	public void deleteAttributes(String key) {
		for (Integer code1:ladj.keySet()) {
			ladj.get(code1).removeAttributeOnNode(key) ;
			for (Integer code2:ladj.get(code1).getEdges().keySet()) {
				ladj.get(code1).removeAttributeOnEdge(code2,key) ;
			}
		}
	}
	
	/** Is there an edge between nodes {@code node1} and {@code node2} 
	 * considering any information stored in the graph ?
	 */
	public boolean hasEdge(N node1, N node2) {
		Integer code1 = getCode(node1) ;
		Integer code2 = getCode(node2) ;
		if (ladj.containsKey(code1) && ladj.containsKey(code2)) {
			return ladj.get(code1).getAttributesOnEdge(code2)!=null ;
		}
		else return false ;
	}
	
	/** Is there an edge between nodes {@code node1} and {@code node2} 
	 * considering {@code key} values ?
	 */
	public boolean hasEdge(N node1, N node2, String key) {
		Integer code1 = getCode(node1) ;
		Integer code2 = getCode(node2) ;
		if (ladj.containsKey(code1) && ladj.containsKey(code2)) {
			return ladj.get(code1).getAttributeOnEdge(code2, key)!=null ;
		}
		else return false ;
	}
	
	/** return all descendants of node {@code node}.
	 */
	public LinkedList<N> getAdjacentNodes(N node) {
		LinkedList<N> list= new LinkedList<N>() ;
		for (Integer codedesc:ladj.get(getCode(node)).getEdges().keySet()) {
			list.add(getNode(codedesc)) ;
		}
		return list ;
	}
	
	/** return all descendants of node {@code node} according key .
	 */
	public LinkedList<N> getAdjacentNodes(N node, String key) {
		LinkedList<N> list= new LinkedList<N>() ;
		for (Integer codedesc:ladj.get(getCode(node)).getEdges().keySet()) {
			if (getAttributeOnEdge(node, getNode(codedesc), key) != null)
				list.add(getNode(codedesc)) ;
		}
		return list ;
	}
	
	
	/** return nodes that has a edge towards node {@code node}
	 */
	public LinkedList<N> getIncidenceNodes(N node) {
		LinkedList<N> list = new LinkedList<N>() ;
		for (Integer code2:ladj.keySet()) {
			if (ladj.get(code2).getEdges().containsKey(getCode(node))) {
				list.add(getNode(code2)) ;
			}
		}
		return list ;
	}
	
	/** return nodes that has a edge towards node {@code node}
	 *  according to key {@code key}
	 */
	public LinkedList<N> getIncidenceNodes(N node, String key) {
		LinkedList<N> list = new LinkedList<N>() ;
		for (Integer code2:ladj.keySet()) {
			if (ladj.get(code2).getAttributeOnEdge(getCode(node), key)!=null) {
				list.add(getNode(code2)) ;
			}
		}
		return list ;
	}
	
	/** Return all nodes stored in the graph.
	 */
	public LinkedList<N> getNodes() {
		LinkedList<N> list= new LinkedList<N>() ;
		for (Integer code:ladj.keySet()) {
			list.add(getNode(code)) ;
		}
		return list ;
	}
	
	@Override
	public String toString() {
		String graphText ="" ;
		for (Integer code1:ladj.keySet()) {
			graphText+=getNode(code1)+"{";
			for (String keynode:ladj.get(code1).getAttributesOnNode().keySet()) {
				graphText+= "(" + keynode +"," + ladj.get(code1).getAttributeOnNode(keynode)+")," ;
			}
			graphText+="} :\t{" ;
			for (Integer code2:ladj.get(code1).edges.keySet()) {
				graphText+="->"+getNode(code2)+ladj.get(code1).getAttributesOnEdge(code2)+"," ;
			}
			graphText+="}\n" ;
		}
		return graphText ;
	}
	
	// create a wrapper class for a node that will contain
		// marks for that node and edges.
		// A node can contain several informations represented 
		// by a HashMap<String, Object>
		// An edge can contain several informations represented 
		// by a HashMap<String, Object>
		private class nodeInfo<N> {
			private N node ;
			private HashMap<String,Object> properties ;
			private LinkedHashMap<Integer,HashMap<String,Object>> edges ;
			
			private nodeInfo() {}
			public nodeInfo(N value) {
				properties = new HashMap<String, Object>() ;
				//properties.put(privateKey, value) ;
				node = value ;
				edges = new LinkedHashMap<Integer,HashMap<String,Object>>() ;
			}
			
			public N getNode() {
				return node ;
				//return (N)properties.get(privateKey) ;
			}
			
			public Object getAttributeOnNode(String key) {
				return properties.get(key) ;
			}
			
			public HashMap<String,Object> getAttributesOnNode() {
				return properties;
			}
			
			public HashMap<Integer,HashMap<String,Object>> getEdges() {
				return edges ;
			}
			
			public void addAttributeOnNode(String key, Object value) {
				properties.put(key, value) ;
			}
			
			public void removeAttributeOnNode(String key) {
				properties.remove(key) ;
			}
			
			public Object getAttributeOnEdge(Integer code2, String key) {
				if (edges.containsKey(code2))
					return edges.get(code2).get(key) ;
				else return null ;
			}
			
			public HashMap<String, Object> getAttributesOnEdge(Integer code2) {
				if (edges.containsKey(code2))
					return edges.get(code2) ;
				else return null ;
			}
			
			public void addAttributeOnEdge(Integer code2, String key, Object value) {
				if (!edges.containsKey(code2)) {
					edges.put(code2, new HashMap<String,Object>()) ; 
				}
				edges.get(code2).put(key, value) ;
			}
			
			public void removeAttributeOnEdge(Integer code2, String key) {
				if (edges.containsKey(code2)) {
					edges.get(code2).remove(key) ;
				}
			}
			
			public void removeEdge(Integer code2) {
				if (edges.containsKey(code2)) {
					edges.get(code2).clear() ;
					edges.remove(code2) ;
				}
			}
			
			public void clearAttributesOnNode() {
				properties.clear();
			}
			
			public void clearAttributesOnEdge(Integer node2) {
				if (edges.containsKey(node2))
					edges.get(node2).clear();
			}
			public void clearAllAttributes() {
				clearAttributesOnNode(); 
				for (Integer node2:edges.keySet()) {
					clearAttributesOnEdge(node2) ;
				}
			}
		}	
}
