package counting_it_up;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositiveBigIntTest {

    PositiveBigInt a = new PositiveBigInt("3");
    PositiveBigInt b = new PositiveBigInt("5");

    @Test
    void mulTest() {
        PositiveBigInt g = new PositiveBigInt("12345678910234567890");
        assertEquals("0", a.mul(new PositiveBigInt("0")).getValue());
        assertEquals("3", a.mul(new PositiveBigInt("1")).getValue());
        assertEquals("9", a.mul(new PositiveBigInt("3")).getValue());
        assertEquals("9", a.mul(new PositiveBigInt("3")).getValue());
        assertEquals("0", new PositiveBigInt("0").mul(new PositiveBigInt("0")).getValue());
        assertEquals("24691357820469135780", g.mul(new PositiveBigInt("2")).getValue());
    }

    @Test
    void factorialTest() {
        assertEquals("1", new PositiveBigInt("0").factorial().getValue());
        assertEquals("1", new PositiveBigInt("1").factorial().getValue());
        assertEquals("2", new PositiveBigInt("2").factorial().getValue());
        assertEquals("120", new PositiveBigInt("5").factorial().getValue());
        assertEquals("720", new PositiveBigInt("6").factorial().getValue());
        assertEquals("3628800", new PositiveBigInt("10").factorial().getValue());
        assertEquals("620448401733239439360000", new PositiveBigInt("24").factorial().getValue());
    }

    @Test
    void stripZerosTest() {
        assertEquals("1", a.stripZeros("1"));
        assertEquals("1", a.stripZeros("01"));
        assertEquals("1", a.stripZeros("0000001"));
        assertEquals("0", a.stripZeros("00"));
        assertThrows(AssertionError.class, () -> a.stripZeros(""));
    }

    @Test
    void isSmallerThanTest() {
        assertTrue(a.isSmallerThan(b));
        assertFalse(a.isSmallerThan(new PositiveBigInt("2")));
        assertFalse(b.isSmallerThan(a));
    }

    @Test
    void divTest() {
        PositiveBigInt g = new PositiveBigInt("12345678910234567890");
        assertEquals("1", a.div(a).getValue());

        assertEquals("6", new PositiveBigInt("6").div(new PositiveBigInt("1")).getValue());
        assertEquals("3", new PositiveBigInt("6").div(new PositiveBigInt("2")).getValue());
        assertEquals("1", new PositiveBigInt("9").div(new PositiveBigInt("6")).getValue());
        assertEquals("4", new PositiveBigInt("12").div(new PositiveBigInt("3")).getValue());
        assertEquals("5", new PositiveBigInt("100").div(new PositiveBigInt("20")).getValue());
        assertEquals("190", new PositiveBigInt("2432902008176640000").div(new PositiveBigInt("12804747411456000")).getValue());
    }

    @Test
    void diffTest() {
        assertEquals("16", (new PositiveBigInt("25").diff(new PositiveBigInt("9")).getValue()));
        assertEquals("2", b.diff(a).getValue());
        assertEquals("0", b.diff(b).getValue());
    }

    @Test
    void addTest() {
        assertEquals("1", PositiveBigInt.addValues("0", "1"));
        assertEquals("5", PositiveBigInt.addValues("4", "1"));
        assertEquals("10", PositiveBigInt.addValues("9", "1"));
        assertEquals("10", PositiveBigInt.addValues("1", "9"));
        assertEquals("11", PositiveBigInt.addValues("1", "10"));
        assertEquals("25", PositiveBigInt.addValues("15", "10"));
    }
}