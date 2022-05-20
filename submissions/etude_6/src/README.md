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
- To implement Pascals triangle we stored numbers as Longs, which allows combinations up to 64 bits. From there, finding
  the number of combinations is relatively trivial

## Using the program

- Compile and then run using instructions, you will then be guided via prompts.
- For entering n and k, enter both n and k in one line. Eg "10 2", will be interpreted as n=10, k=2

2^31-1

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
        assertEquals(922337203685477580L, c.combinations(922337203685477580L, 1));
    }
}

```

## External Sources and libraries

- Other than the aforementioned JUnit 5.7, we relied on the built in standard java libraries to implement this program.