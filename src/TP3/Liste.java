package TP3;

import java.lang.reflect.Array;
import java.util.Iterator;


/**
 * An empty list is an instance of class {@code Liste} where {@code head=queue=null}.
 * A list containing one element {@code v} is represented by 2 objects of {@code Liste} where :
 * <ul> 
 * <li>{@code v} is stored in {@code head} attribute of first object</li>
 * <li>queue attribute of first object refers to second {@code Liste} object where head=queue=null.</li>
 * </ul> 
 * @author Dr. Denis PALLEZ </br> 
 * {@link http://denispallez.i3s.unice.fr}
 * @version 2017-2018
 */

public class Liste<E> { // 
	private E tete;
	private Liste<E> queue;
	
	public Liste() {
		tete = null;
		queue = null;
	}
	public Liste(E n, Liste<E> suivante) {
		tete=n ;
		queue = suivante ;
	}
	public E getTete() {
		return tete;
	}
	public Liste<E> getQueue() {
		return queue;
	}
	public boolean estVide() {
		return (tete==null) ;
	}
	
	@Override
	public String toString() {
		if (estVide()){
			return "null";
		}
		else {
			return "("+getTete()+ ", " + getQueue()+")";
		}
	}
	
	public int taille() {
		if (estVide())
			return 0;
		else
			return 1 + queue.taille();
	}
	public boolean contient(E v) {
		if(estVide()) {
			return false;
		} else if(this.tete == v) {
			return true;
		} else {
			return this.getQueue().contient(v);
		}
	}
	public Liste<E> ajouteTete(E v) {
		if (this.estVide())
			return new Liste<E>(v,new Liste<E>()) ;
		else 
			return new Liste<E>(v,this.getQueue().ajouteTete(getTete())) ;
	}
	
	public void ajouteTeteProc(E v) {
		if(this.estVide()) {
			this.tete = v;
			this.queue = null;
		} else {
			Liste<E> tmp = new Liste<E>(this.getTete(), this.getQueue());
			this.tete = v;
			this.queue = tmp;
		}
	}
	
	public Liste<E> ajouteQueue(E v) {
		if(this.estVide()) {
			return new Liste<E>(v, new Liste<E>());
		} else {
			return new Liste<E>(this.tete, queue.ajouteQueue(v));
		}
	}
	
	public Liste<E> suppTete() {
		if (estVide())
			return new Liste<E>() ;
		else if (this.getQueue().estVide())
			return new Liste<E>() ;
		else
			return this.getQueue().getQueue().ajouteTete(getQueue().getTete()) ;
	}
	
	public void ajouteQueueProc(E v) {
		if (estVide()) {
			tete = v;
			queue = new Liste<E>() ;
		}
		else
			queue.ajouteQueueProc(v);
	}
	
	public Liste<E> supprime(E v) {
		Liste<E> l = new Liste<E>();
		//TO DO
		return l;
	}

	
	public void suppTeteproc() {
		//TO DO
	}
	
	public Liste<E> suppQueue() {
		if(this.estVide()) { //cas vide
			return new Liste<E>();
		} else if(this.getQueue().estVide()) {//cas avec un seul élément
			return new Liste<E>(); 
		} else { //il faut au moins copier la tête caril y a un autre élément derrière
			return new Liste<E>(this.getTete(), this.getQueue().suppQueue());
		}
	}
	
	public void suppQueueProc() {
		if (estVide()) {}
		else if (getQueue().estVide()) {
			tete=null; 
			queue.finalize();
			queue=null ;
		}
		else getQueue().suppQueueProc(); 
	}
	
	public E get(int pos) {
		if (estVide())
			return null ;
		else if (pos==0) return tete ;
		else return getQueue().get(pos-1) ; 
	}
	
	public void efface() {
		//TO DO
	}
	
	public Liste<E> ajouteTous(Liste<E> l) {
		Liste<E> ll = new Liste<E>();
		//TO DO
		return ll;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof Liste<?>) {
			Liste<E> param = (Liste<E>)(o) ;
			if (estVide() && param.estVide()) 
				return true;
			else if (!estVide() && !param.estVide()) {
				if (tete.equals(param.tete))
					return queue.equals(param.queue) ;
				else return false ;
			}
			else return false ;
		
		}
		return false ;
	}
	
	public void supprimeProc(E v) {
		//TO DO
	}
	
	public Liste<E> supprimeTous(E v) {
		Liste<E> l = new Liste<E>();
		//TO DO
		return l;
	}
	
	public void supprimeTousProc(E v) {
		//TO DO
	}
	
	public Liste<E> inverse() {
		Liste<E> l = new Liste<E>();
		//TO DO
		return l;
	}

	public void inverseProc() {
		//TO DO
	}
	
	public void finalize() {
		efface() ;
	}
	
	public E[] toArray() {
		Liste<E> l=this ;
		@SuppressWarnings("unchecked")
		E[] array = (E[])Array.newInstance(tete.getClass(),l.taille()) ;
		int i=0 ;
		while (!(l.estVide())) {
			array[i]=l.tete;
			l=l.queue;
			i++ ;
		}
		return array ;
	}
	
	public Liste<Integer> delMultiple(int n) {
		if(!this.estVide()) {
			if((Integer)this.getTete()%n == 0) {
				return this.getQueue().delMultiple(n);
			} else {
				return new Liste<Integer>((Integer)this.getTete(), this.getQueue().delMultiple(n));
			}
		} else {
			return new Liste<Integer>();
		}
	}
	
	public Liste<Integer> remplirListe(int n) {
		if(n <= 1) {
			return new Liste<Integer>();
		} else {
			return remplirListe(n-1).ajouteQueue(n);
		}
	}
	
}
