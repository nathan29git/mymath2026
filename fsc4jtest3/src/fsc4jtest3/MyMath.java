package fsc4jtest3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyMath {
	
	/**
	 * Dit is een methode om de vierkantswortel te berekenen, naar beneden afgerond, van het gegeven niet-negatieve geatl
	 * @throws IllegalArgumentException = DEFENSIEF PROGRAMMEREN | x<0
	 * //@pre Het gegeven getal is niet negatief. = CONTRACTUEEL PROGRAMMEREN
	 * //	| 0 <= x 
	 * @post Het resultaat is de vierkantswortel van het gegeven getal,
	 * 		 afgerond naar beneden. Het kwadraat van het resultaat is niet groter dan x EN 
	 * 		 het kwadraat van één meer dan het getal is groter dan x 
	 * 	| result * result <= x &&
	 * 	| x < (result + 1) * (result + 1)
	 * 
	 */
	public static int sqrt(int x) {
//		if (x < 0)
//			throw new IllegalArgumentException("`x`kleiner dan 0"); //lukt enkel bij nofsc4j dus gaan we ook niet echt gebruiken
		int result = 0;
		while ((result + 1) * (result + 1) <= x )
			result++;
		return result;
	}
	
	/**
	 * @pre | true
	 * @post Het resultaat is het grootste van de
	 *       gegeven getallen
	 *     | result >= x && result >= y && result >= z
	 *     | &&
	 *     | (result == x || result == y || result == z)
	 */
	static int max(int x, int y, int z) {
		if (x >= y)
			if (x >= z)
				return x;
			else
				return z;
		else
			if (y >= z)
				return y;
			else
				return z;
	}
	
	/**
	 * geeft de kleinste index terug waarvoor `needle` voorkomt in `haystack` of -1 als `needle` niet voorkomt in `haystack`.
	 *
	 * //@pre `haystack`is niet null
	 * 	//| haystack != null
	 * @throws IllegalArgumentException | haystack == null
	 * @inspects |haystack
	 * @post Het 
	 * 	| result == -1 || 0 <= result && result < haystack.length
	 * @post | result == -1 || haystack[result] == needle
	 * @post Voor alle indices `i` tussen 0 (inclusief) en `result`(exclusief) geldt dat `haystack` op
	 *  index `i` niet gelijk is aan `needle`.
	 * 	| IntStream.range(0,result).allMatch(i -> haystack[i] != needle)
	 * @post |result != -1 || IntStream.range(0, haystack.length).allMatch(i -> haystack[i] != needle)
	 * 
	 */
	
	public static int find(int[] haystack, int needle) {
		int index = 0;
		for(;;) {
			if (index == haystack.length)
				return -1;
			if (haystack[index] == needle)
				return index;
			index ++;
		}
	}
	
	/**
	 * @pre | array != null
	 * @inspects | array
	 * @post
	 *    | result == 
	 *    | IntStream.range(0, array.length - 1).allMatch(i ->
	 *    |     array[i] <= array[i + 1]
	 *    | )
	 */
	static boolean isSorted(int[] array) {
		for (int i = 1; i < array.length; i++)
			if (array[i - 1] > array[i])
				return false;
		return true;
	}

	
	/**
	 * Vervangt elke element van de gegeven array door haar tegengestelde
	 * 
	 * 
	 * @pre | numbers != null
	 * @post Voor elke geldige index `i` in numbers geldt dat numbers op index i na afloop gelijk is aan de negatie van numbers op index i bij oproep.
	 * 	|IntStream.range(0, numbers.length).allMatch( i -> numbers[i] == -old(numbers.clone())[i])
	 * @param numbers
	 */
	public static void negate (int[] numbers) {
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = -numbers[i];
	}
	
	/**
	 * @pre | array != null
	 * @pre | 0 <= index
	 * @pre | index < array.length
	 * 
	 * @mutates | array
	 * 
	 * @post | array[index] == value
	 * @post | IntStream.range(0, array.length).allMatch(i ->
	 *       |     i == index || array[i] == old(array.clone())[i]
	 *       | )
	 */
	static void set(int[] array, int index, int value) {
		array[index] = value;
	}
	
	/**
	 * @pre | array != null
	 * @post | result ==
	 *       | IntStream.range(0, array.length).filter(i -> array[i] == 0).count()
	 */
	static int countZeros(int[] array) {
		int result = 0;
		for (int i = 0; i < array.length; i++)
			if (array[i] == 0)
				result++;
		return result;
	}
	
	
	/**
	 * Voegt het element van `array` op index `n` in in het gesorteerde segment van `array` tussen index
	 * 0 en `n - 1`. Schuift de gesorteerde elementen groter dan `n` één plaats naar rechts op.
	 * 
	 * @pre | array != null
	 * @pre | 0 <= n && n < array.length
	 * @pre De elementen van `array` op indices 0 tot en met `n - 1` staan gesorteerd.
	 *      | IntStream.range(0, n - 1).allMatch(i -> array[i] <= array[i + 1])
	 * @post De elementen van `array` op indices 0 tot en met `n` staan gesorteerd.
	 *      | IntStream.range(0, n).allMatch(i -> array[i] <= array[i + 1])
	 * @post Het stuk van de array op indices 0 tot en met `n` bevat dezelfde elementen als voor de oproep,
	 *       en met hetzelfde aantal voorkomens.
	 *      | IntStream.range(0, n + 1).allMatch(i ->
	 *      |     IntStream.range(0, n + 1).filter(j -> array[j] == array[i]).count() ==
	 *      |     IntStream.range(0, n + 1).filter(j -> old(array.clone())[j] == array[i]).count()
	 *      | ) //hiermee garanderen we dat er noch elementen zijn toegevoegd, nog verdwenen
	 * @post De elementen op indices groter dan `n` zijn ongewijzigd.
	 *      | Arrays.equals(array, n + 1, array.length, old(array.clone()), n + 1, array.length)
	 */
	static void insert(int[] array, int n) {
		int i = n;
		int toInsert = array[n];
		while (1 <= i && array[i-1] > toInsert) {
			array[i] = array[i-1];
			i--;
		}
		array[i] = toInsert;
	}
	
	/**
	 * Sorteert de gegeven array, gebaseerd op sort //(dus komt eigenlijk overeen met het insertion sort algorithm)
	 * 
	 * Deze methode zet de elementen van `array` in de juiste volgorde.
	 * 
	 * @pre | array != null
	 * @post | IntStream.range(0, array.length - 1).allMatch(i -> array[i] <= array[i + 1])
	 * @post |
	 *      | IntStream.range(0, array.length).allMatch(i ->
	 *      |     IntStream.range(0, array.length).filter(j -> array[j] == array[i]).count() ==
	 *      |     IntStream.range(0, array.length).filter(j -> old(array.clone())[j] == array[i]).count()
	 *      | )
	 */
	static void sort(int[] array) {
		for (int i = 1; i < array.length; i++)
			insert(array, i);
	}
	
	
}
