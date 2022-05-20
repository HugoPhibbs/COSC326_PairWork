package counting_it_up;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CountingItUpTest {
    CountingItUp c = new CountingItUp();


    @Test
    void combinationsTest() {
        assertEquals(5, c.combinations(5, 1));
        assertEquals(20, c.combinations(6, 3));
        assertEquals(100, c.combinations(100, 1));
        assertEquals(7219428434016265740L, c.combinations(66, 33));
        assertEquals(3921225L, c.combinations(100, 4));
        // assertThrows(AssertionError, () -> .class InnerCountingItUpTest {
    }

    @Test
    void kBiggerThenNTest() {
        assertEquals(0, c.combinations(1, 5));
        assertEquals(0, c.combinations(1, 100));
        assertEquals(0, c.combinations(33, 66));
        assertEquals(0, c.combinations(4, 100));
    }

    @Test
    void BigNumberTest() {
        assertEquals(7219428434016265740L, c.combinations(66, 33)); // Max number of combinations
        assertEquals(1, c.combinations(100, 100));
        assertEquals(1660305826125766950L, c.combinations(180, 12));
        assertEquals(9202167919706100768L, c.combinations(1733, 7));
        assertEquals(2147483647, c.combinations(2147483647, 1));
        assertEquals(9223372036854775807L, c.combinations(9223372036854775807L, 1));
    }
}
