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
javac -d out -cd src src/counting_it_up/CountingItUp.java
```

- Then to run, enter:

```shell
java -cp out counting_it_up/CountingItUp
```

## Tests

- We implemented tests using JUnit 5.7

```java
// TODO
```

## External Sources and libraries

- Other than the aforementioned JUnit 5.7, we relied on the built in standard java libraries to implement this program.