package TP6;

public class MainTP6 {
	public static void main(String[] args) {
		Graph<Integer> g = new Graph<>(true);
		final String key1 = "v";
		final String key2 = "*";
		
		g.addAttributeOnEdge(67, 21, key1, 93);
		g.addAttributeOnEdge(21, 67, key1, 76);
		g.addAttributeOnEdge(78, 21, key1, 20);
		g.addAttributeOnEdge(78, 21, key2, Boolean.TRUE);
		g.addAttributeOnEdge(21, 21, key2, 58);
		
		System.out.println(g.toString());
	}
}
