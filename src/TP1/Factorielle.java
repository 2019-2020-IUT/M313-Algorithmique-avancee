package TP1;

public class Factorielle {

	public static int fact(int n) {
		if(n < 0) {
			return (int)Math.pow(-1, Math.abs(n)) * fact(Math.abs(n));
		} else if (n == 0){
			return 1;
		} else {
			return n * fact(n-1);
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(fact(-1));
		System.out.println(fact(-3));
		System.out.println(fact(-4));
		System.out.println(fact(-5));
		System.out.println(fact(0));
	}
}


