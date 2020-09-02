package TP1;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame; 

public class FenetreG extends JFrame {
	static Random generator = new Random() ;
	
	
	public void paint(Graphics g) {
		menger(g, 400, 50, (int) Math.pow(3, 6));
	}
	
	
	public void menger(Graphics g, int x, int y, int taille) {
		if (taille <= 1) {
			g.drawRect(x, y, 1, 1);
		} else {
			menger(g, x, y, taille/3);
			menger(g, x, y + taille/3, taille/3);
			menger(g, x, y + 2 * taille/3, taille/3);
			
			menger(g, x + taille/3, y, taille/3);
			menger(g, x + taille/3, y + 2 * taille/3, taille/3);
			
			menger(g, x + 2 * taille/3, y, taille/3);
			menger(g, x + 2 * taille/3, y + taille/3, taille/3);
			menger(g, x + 2 * taille/3, y + 2 * taille/3, taille/3);
			
		}
	}
	
	public static void main(String[] args) {
		FenetreG fenetre = new FenetreG() ;
		fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		fenetre.setExtendedState(MAXIMIZED_BOTH) ;
		fenetre.setVisible(true) ;
		fenetre.repaint();
	}
}