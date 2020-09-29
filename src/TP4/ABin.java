package TP4;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This class implements a binary tree containing elements of type {@code T}
 * @author Dr. Denis PALLEZ </br> 
 * {@link http://denispallez.i3s.unice.fr}
 * @version 2016-2017
 * 
 * @param <T>
 */

public class ABin<T> {
	T value ;
	ABin<T> sag;
	ABin<T> sad;

	public ABin() {
		value=null;
		sag=null;
		sad=null ;
	}
	
	public ABin(T val, ABin<T> fg, ABin<T> fd) {
		value = val;
		sag=fg ; 
		sad=fd ;
	}
	
	public ABin(T[] t) {
		ABin<T> a = tas(t, 0);
		this.value = a.value;
		this.sag = a.leftChild();
		this.sad = a.rightChild();
	}
	
	private ABin<T> tas(T[] t, int pos) {
		if(pos < t.length) {
			return new ABin<T>(t[pos], tas(t, 2 * pos + 1), tas(t, 2 * pos + 2));
		} else {
			return new ABin<T>();
		}
	}
	
	public static int alea(int min, int max) {
		return (int) (min + Math.random()* (max-min));
	}
	
	public static ABin<Integer> createRandom(int nb) {
		if(nb <= 0) {
			return new ABin<>();
		} else {
			if(alea(0, 2) == 1) {
				return new ABin<>(alea(0, 100), createRandom(nb-1), new ABin<>());
			} else {
				return new ABin<>(alea(0, 100), createRandom(nb-1), createRandom(nb-2));
			}
		}
	}

	
	public boolean isEmpty() {
		return (value==null) ;
	}
	
	public boolean isLeaf() {
		return !isEmpty() && sag.isEmpty() && sad.isEmpty() ;
	}
	
	public ABin<T> leftChild() {
		return sag ;
	}
	
	public ABin<T> rightChild() {
		return sad ;
	}
	
	public T getUserObject() {
		return value ;
	}
	
	public void setUserObject(T val) {
		value=val ;
	}
	
	public int size() {
		if(this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.leftChild().size() +this.rightChild().size();
		}
	}
	
	public int getDepht() {
		if(this.isEmpty()) {
			return 0;
		} else {
			return 1+ Math.max(this.leftChild().getDepht(), this.rightChild().getDepht());
		}
	}
	
	@Override
	public String toString() {
		if(this.isEmpty()) {
			return "vide";
		} else {
			return "(" + this.value + "," + this.leftChild().toString() + "," + this.rightChild().toString() + ")";
		}
	}
	
	public boolean contains(T v) {
		if(this.isEmpty()) {
			return false;
		} else {
			if(this.getUserObject().equals(v)) {
				return true;
			} else {
				return (this.leftChild().contains(v) || this.rightChild().contains(v));
			}
		}
	}
	
	public DefaultMutableTreeNode toNTree() {
		if (!isEmpty()) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(value.toString()) ;
			if (!sag.isEmpty()) node.add(sag.toNTree()) ;
			if (!sad.isEmpty()) node.add(sad.toNTree()) ;
			return node ;
		}
		else return null ;
	}
	
	public void showInFrame() {
		JFrame f= new JFrame("A N-ary Tree vizualized with JTree");
		f.setSize(800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		// 	Ajouter l'arbre a ce cadre
		f.getContentPane().add(new JTree(this.toNTree()));
		// Visualiser le cadre
		f.setVisible(true);
	}
}
