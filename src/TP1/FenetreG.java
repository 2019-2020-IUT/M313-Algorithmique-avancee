package TP1;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class FenetreG extends JFrame {
	static Random generator = new Random() ;
	
	public void paint(Graphics g) {
	}
	
	public static void main(String[] args) {
		FenetreG fenetre = new FenetreG() ;
		fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		fenetre.setExtendedState(MAXIMIZED_BOTH) ;
		fenetre.setVisible(true) ;
		fenetre.triGraphique();
		fenetre.repaint();
	}
}