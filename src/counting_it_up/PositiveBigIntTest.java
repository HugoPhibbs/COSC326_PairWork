package counting_it_up;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositiveBigIntTest {

    PositiveBigInt a = new PositiveBigInt("3");
    PositiveBigInt b = new PositiveBigInt("5");

    @Test
    void stripZerosTest() {
        assertEquals("1", PositiveBigInt.stripZeros("1"));
        assertEquals("1", PositiveBigInt.stripZeros("01"));
        assertEquals("1", PositiveBigInt.stripZeros("0000001"));
        assertEquals("0", PositiveBigInt.stripZeros("00"));
        assertThrows(AssertionError.class, () -> PositiveBigInt.stripZeros(""));
    }

    @Test
    void incrementTest() {
        a.increment();
        assertEquals("4", a.getValue());
    }

    @Test
    void isSmallerThanTest(){
        assertTrue(new PositiveBigInt("1").isSmallerThan(new PositiveBigInt("2")));
        assertTrue(new PositiveBigInt("1").isSmallerThan(new PositiveBigInt("1000")));
        assertTrue(new PositiveBigInt("0").isSmallerThan(new PositiveBigInt("2")));
        assertFalse(new PositiveBigInt("1").isSmallerThan(new PositiveBigInt("1")));
    }

    @Test
    void addTest() {
        assertEquals(new PositiveBigInt("0"), new PositiveBigInt("0").add(new PositiveBigInt("1")));
        assertEquals(new PositiveBigInt("1"), new PositiveBigInt("1").add(new PositiveBigInt("0")));
        assertEquals(new PositiveBigInt("2"), new PositiveBigInt("1").add(new PositiveBigInt("1")));
        assertEquals(new PositiveBigInt("5"), new PositiveBigInt("4").add(new PositiveBigInt("1")));
        assertEquals(new PositiveBigInt("10"), new PositiveBigInt("9").add(new PositiveBigInt("1")));
        assertEquals(new PositiveBigInt("10"), new PositiveBigInt("1").add(new PositiveBigInt("9")));
        assertEquals(new PositiveBigInt("101"), new PositiveBigInt("99").add(new PositiveBigInt("2")));
        assertEquals(new PositiveBigInt("11"), new PositiveBigInt("1").add(new PositiveBigInt("10")));
        assertEquals(new PositiveBigInt("25"), new PositiveBigInt("15").add(new PositiveBigInt("10")));
        // Test out of range integers
        assertEquals(new PositiveBigInt("16666666669999999999999999999999"), new PositiveBigInt("16666666666666666666666666666666").add(new PositiveBigInt("3333333333333333333333")));
        assertEquals(new PositiveBigInt("16666666669999999999999999999999"), new PositiveBigInt("3333333333333333333333").add(new PositiveBigInt("16666666666666666666666666666666")));
    }
}