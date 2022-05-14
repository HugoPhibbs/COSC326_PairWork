# **Etude 6: Counting It Up**

*By Hugo Phibbs and Ben Forde*

# Our approach

- We started off with explicitly using the formula to find the number of combinations. For this we attempted to
  implement a BigPositiveInteger class with similar functionality to BigInteger, complete with the ability for number
  arithmetic. This proved to harder than expected, as we need to implement arithmetic using just strings of integers.
  Especially complex was division, our algorithm relied on repeated subtraction, which was just far too slow.
- We did some more research, and some tips from classmates that we could instead use Pascals triangle to solve this.
  This is detailed in this article: https://brilliant.org/wiki/binomial-coefficient/. This was much easier to implement,
  as it only required adding numbers together, no subtraction, multiplication nor division.
- For this, we used PositiveBigInt objects to store n and k. While the number of combinations couldn't exceed 64 bits,
  inputs of n and k could, using PositiveBigInt accounted for this. PositiveBigInt gave us the ability to handle and add
  numbers together that weren't 64 bits in size.

## Using the program

- Compile and then run using instructions, you will then be guided via prompts.
- For entering n and k, enter both n and k in one line. Eg "10 2", will be interpreted as n=10, k=2

## Running and Compilation

- To compile, enter in your command console:

```shell
javac -d out -cp src src/counting_it_up/CountingItUp.java
```

- Then to run, enter:

```shell
java -cp out counting_it_up/CountingItUp
```

## Tests

- We implemented tests using JUnit 5.7

- Bellow is the main JUnit test class:

```java
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
    assertEquals(0,  c.combinations(new PositiveBigInt("1"),new PositiveBigInt("5")));
    assertEquals(0,  c.combinations(new PositiveBigInt("1"),new PositiveBigInt("100")));
    assertEquals(0,  c.combinations(new PositiveBigInt("33"),new PositiveBigInt("66")));
    assertEquals(0,  c.combinations(new PositiveBigInt("4"),new PositiveBigInt("100")));

  }

  @Test
  void BigNumberTest(){
    assertEquals(1,  c.combinations(new PositiveBigInt("100"),new PositiveBigInt("100")));
    assertEquals(1660305826125766950L,  c.combinations(new PositiveBigInt("180"),new PositiveBigInt("12")));
    assertEquals(1,  c.combinations(new PositiveBigInt("1660305826125766950"),new PositiveBigInt("1660305826125766950")));
  }
}

```

- And here is the JUnit test class for PositiveBigInt

```java
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
        assertEquals(new PositiveBigInt("1"), new PositiveBigInt("0").add(new PositiveBigInt("1")));
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
```

## External Sources and libraries

- Other than the aforementioned JUnit 5.7, we relied on the built in standard java libraries to implement this program.