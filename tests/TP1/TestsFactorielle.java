package TP1;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class TestsFactorielle {

	private String s1 = "";
	private String s2 = "a";
	private String s3 = "ab";
	private String s4 = "abba";
	private String s5 = "slttls";
	private String s6 = "radar";
	private String s7 = "applejack";
	
	@Test
	public void testVrais() {
		assertTrue(Palindrome.pal(s1));
		assertTrue(Palindrome.pal(s2));
		assertTrue(Palindrome.pal(s4));
		assertTrue(Palindrome.pal(s5));
		assertTrue(Palindrome.pal(s6));
	}
	
	@Test
	public void testFaux() {
		assertTrue(Palindrome.pal(s3));
		assertTrue(Palindrome.pal(s7));
	}
	
}
