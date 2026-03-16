package mymath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {
	
	
	@Test
	void testSqrt() {
		assertEquals(3, MyMath.sqrt(9));
		assertEquals(3, MyMath.sqrt(10));
		assertEquals(4, MyMath.sqrt(16));
	}
	
	
	
	@Test
	void testMax() {
		assertEquals(3, MyMath.max(1, 2, 3));
		assertEquals(3, MyMath.max(1, 3, 2));
		assertEquals(3, MyMath.max(3, 1, 2));
		assertEquals(3, MyMath.max(2, 1, 3));
	}
	
	@Test	//dit is uit de oplossingen van 2026
	void testFind() {
		assertEquals(3, MyMath.find(new int[] {10, 3, 7, 13}, 13));
		assertEquals(0, MyMath.find(new int[] {10, 3, 7, 13}, 10));
		assertEquals(-1, MyMath.find(new int[] {10, 3, 7, 13}, 20));
	}
	
		/*
		@Test	// dit is uit lesopname 2025
		void testFind() {
			int[] numbers = {3, 13, 7, 9};
			assertEquals(1, MyMath.find(numbers, 13));
			assertEquals(-1, MyMath.find(numbers, 8));
		}
	 	*/	
	
	@Test 
	void testIsSorted() {
		assertEquals(true, MyMath.isSorted(new int[] {5}));
		assertEquals(false, MyMath.isSorted(new int[] {5, 3}));
	}
	
	@Test
	void testSet() {
		int[] xs = {10, 20, 30, 40};
		MyMath.set(xs, 2, 100);
		assertArrayEquals(new int[] {10, 20, 100, 40}, xs);
	}
	
	
	@Test
	void testInsert() {
		int[] xs = {10, 20, 30, 15, -7};
		MyMath.insert(xs, 3);
		assertArrayEquals(new int[] {10, 15, 20, 30, -7}, xs);
	}
	
	@Test
	void testSort() {
		int[] xs = {15, 10, 30, 20, -7};
		MyMath.sort(xs);
		assertArrayEquals(new int[] {-7, 10, 15, 20, 30}, xs);
	}
	
	
	
	@Test
	void testNegate() {
		int[] numbers = {3, -13, -7, 9};
		MyMath.negate(numbers);
		assertArrayEquals (new int[] {-3, 13, 7, -9}, numbers);
	}
	

}
