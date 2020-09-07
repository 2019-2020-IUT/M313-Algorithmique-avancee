package TP2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** This class contains testing methods for sorting.<br>
 * 
 * @author Dr. Denis Pallez<br>
 * <a href="http://denispallez.i3s.unice.fr">http://denispallez.i3s.unice.fr</a>
 */

public class TrisTestEtudiant {
	// array containing values to be sorted
	protected static int[] nombres;
	// size of the array to be sorted
	protected static final int SIZE = 10;
	// array will contain values in [1, MAX_VALUE]
	protected static final int MAX_VALUE = 10;
	
	// Random number generator
	static Random generator ;

	/** check whether array numbers is correctly sorted or not */
	protected boolean arraySorted(int[] numbers) {
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i] > numbers[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	/** initialize an array with random values*/
	protected void initRandom() {
		for (int i = 0; i < nombres.length; i++) {
			nombres[i] = generator.nextInt(MAX_VALUE);
		}
	}
	
	/** create an array sorted in the decreasing order*/
	protected void initWorst() {
		for (int i = 0; i < nombres.length; i++) {
			nombres[i] = MAX_VALUE-i;
		}
	}
	
	/** init an array with values already sorted*/ 
	protected void initBest() {
		for (int i = 0; i < nombres.length; i++) {
			nombres[i] = i;
		}
	}
	
	/** initialize an array with random values that could appear several times*/
	protected void initSameValues() {
		final int NBVAL_DIFF = nombres.length/2 ;
		int[] values = new int[NBVAL_DIFF];
		for (int i=0;i<NBVAL_DIFF;i++) {
			values[i] = generator.nextInt(MAX_VALUE) ;
		}
		for (int i=0;i<nombres.length;i++) {
			nombres[i] = values[generator.nextInt(NBVAL_DIFF)] ;
		}
	}
	
	public static void triRapide(int[] numbers)  {
		quickSort();
	}
	
	private static void exchange(int[] t, int i, int j) {
		int temp = t[i];
		t[i] = t[j];
		t[j] = temp;
	}
	
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
	
	private static void quickSort(int[] numbers, int min, int max) {
		int i = min, j = max;
		// Get the pivot element from the middle of the list
		int pivot = numbers[min + (max - min) / 2];

		int[] newij = partitionnerPivot(numbers, i,j, pivot) ;
		i=newij[0] ; j=newij[1] ;
		
		// Recursion
		if (min < j)
			quickSort(numbers, min, j);
		if (i < max)
			quickSort(numbers, i, max);
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		generator = new Random() ;
		nombres = new int[SIZE];
		System.out.println("******************") ;
		System.out.println("Test de tris de tableaux à "+SIZE+" éléments:") ;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		generator=null ;
		nombres=null ;
	}
	
	@Test
	public void testRapideRandomCase() {
		initRandom();
		long startTime = System.nanoTime();
		triRapide(nombres);
	    long stopTime = System.nanoTime();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("RandomCase: Tri en " + elapsedTime);
	    if (!arraySorted(nombres)) {
	      fail("Should not happen");
	    }
	    assertTrue(true);
	}
	
	@Test
	public void testRapideWorstCase() {
		initWorst();
		long startTime = System.nanoTime();
		triRapide(nombres);
	    long stopTime = System.nanoTime();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("RandomCase: Tri en " + elapsedTime);
	    if (!arraySorted(nombres)) {
	      fail("Should not happen");
	    }
	    assertTrue(true);
	}
	
	@Test
	public void testFusionRandomCase() {
		initRandom();
		long startTime = System.nanoTime();
		triFusion(nombres);
	    long stopTime = System.nanoTime();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("RandomFusionCase: Tri en " + elapsedTime);
	    if (!arraySorted(nombres)) {
	      fail("Should not happen");
	    }
	    assertTrue(true);
	}
	
	@Test
	public void testQuicksort() {
		long startTime = System.nanoTime();
		triRapide(nombres) ;
	    long stopTime = System.nanoTime();
	    long elapsedTime = stopTime - startTime;
	    if (!arraySorted(nombres)) {
	      fail("Should not happen");
	    }
	    System.out.println("QuickSort_RandomCase: Tri en " + elapsedTime + " nanoseconds");
	    assertTrue(true);
	}	
}
