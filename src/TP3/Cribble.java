package TP3;

public class Cribble {
	Liste<Integer> l = new Liste<Integer>();
		
	public Cribble(int nb) {
		this.l.remplirListe(nb);
	}
	
	public Liste<Integer> getList() {
		return this.l;
	}
	
	@Override
	public String toString() {
		return this.getList().toString();
	}
	
	public static void main(String[] args) {
		Cribble c = new Cribble(20);
		System.out.println(c.toString());
	}
	
}
