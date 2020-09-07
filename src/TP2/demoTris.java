package TP2;

import java.util.Arrays;

public class demoTris {

	int[] tabA = new int[8];
	tabA[0] = 8;
	tabA[1] = 4;
	tabA[2] = 25;
	tabA[3] = 28;
	tabA[4] = 13;
	tabA[5] = 40;
	tabA[6] = 30;
	tabA[7] = 23;

	public static void triFusion(int[] numbers) {
		mergeSort(numbers, 0, numbers.length);
	}
	
	private static void mergeSort(int[] tab, int min, int max) {
		int mid = 0;
		if(min != max) {
			mid = (min+max)/2;
			
			mergeSort(tab, min, mid);
			mergeSort(tab, mid+1, max);
			
			int[] tmpTab = new int[max-min+1];
			int[] t1 = Arrays.copyOfRange(tab, min, mid+1);
			int[] t2 = Arrays.copyOfRange(tab, mid+1, max);
			
			Fusion(t1, t2, tmpTab);
			
			for(int i=0; i < max-min; i++) {
				tab[min+i] = tmpTab[i];
			}
		}
	}
	
	private static void Fusion(int[] tab1, int[] tab2, int[] tab) {
		int i = 0; int i1 = 0; int i2 = 0;
		
		while(i1 < tab1.length && i2 < tab2.length) {
			if(tab1[i1] < tab2[i2]) {
				tab[i] = tab1[i1];
				i++;i1++;
			} else {
				tab[i] = tab2[i2];
				i++;i2++;
			}
		}

		while(i1<tab1.length) {
			tab[i] = tab1[i1];
			i++;i1++;
		}
		
		while(i1<tab2.length) {
			tab[i] = tab2[i2];
			i++;i2++;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(triFusion(tabA[]));
	}
}
