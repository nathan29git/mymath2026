package fsc4jtest3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {
	
	int sqrt(int x) {
		int result = 0;
		while ((result + 1) * (result + 1) <= x )
			result++;
		return result;
	}
	@Test
	void test() {
		assertEquals(3, sqrt(9));
		assertEquals(3, sqrt(10));
		assertEquals(4, sqrt(16));
	}

}
