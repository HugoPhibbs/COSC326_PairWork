package counting_it_up;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

public class CountingItUpTest {
    CountingItUp c = new CountingItUp();

    
@Test
    void combinationsTest(){
        assertEquals(5,  c.combinations(new PositiveBigInt("5"),new PositiveBigInt("1")));
        assertEquals(100,  c.combinations(new PositiveBigInt("100"),new PositiveBigInt("1")));
        assertEquals(7219428434016265740L,  c.combinations(new PositiveBigInt("66"),new PositiveBigInt("33")));
        assertEquals(3921225L,  c.combinations(new PositiveBigInt("100"),new PositiveBigInt("4")));
       // assertThrows(AssertionError, () -> .class InnerCountingItUpTest {
       }
@Test
    void kBiggerThenNTest(){
        assertEquals(5,  c.combinations(new PositiveBigInt("1"),new PositiveBigInt("5")));
        assertEquals(100,  c.combinations(new PositiveBigInt("1"),new PositiveBigInt("100")));
        assertEquals(7219428434016265740L,  c.combinations(new PositiveBigInt("33"),new PositiveBigInt("66")));
        assertEquals(3921225L,  c.combinations(new PositiveBigInt("4"),new PositiveBigInt("100")));

    }

    @Test
    void BigNumberTest(){
    assertEquals(1,  c.combinations(new PositiveBigInt("100"),new PositiveBigInt("100")));
    assertEquals(1660305826125766950L,  c.combinations(new PositiveBigInt("180"),new PositiveBigInt("12")));
    assertEquals(1,  c.combinations(new PositiveBigInt("1660305826125766950"),new PositiveBigInt("1660305826125766950")));
    }
}
