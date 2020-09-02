package TP1;

import java.util.Scanner;

public class Palindrome {

	public static String lire() {
		String s;
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();
		sc.close();
		return s;
	}
	
	public static boolean pal(String s) {
		if(s.length() == 0 ||  s.length() == 1) {
			return true;
		} else if(s.charAt(0) == s.charAt(s.length()-1)) {
			return pal(s.substring(1, s.length()-2));
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
			System.out.println(pal(lire()));
	}
}
