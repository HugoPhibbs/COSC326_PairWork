# **CountingItUp**

*By Hugo Phibbs and Ben Forde*

# Our approach

- We started off with explicitly using the formula to find the number of combinations. For this we attempted to
  implement a BigPositiveInteger class with similar functionality to BigInteger, which stored integers as strings. This
  proved to harder than expected, as we need to implement arithmetic using just strings of integers. Especially complex
  was division, our algorithm relied on repeated subtraction, which was just far too slow.
- We did some more research, and some tips from classmates that we could instead use Pascals triangle to solve this.
  This is detailed in this article: https://brilliant.org/wiki/binomial-coefficient/. This was much easier to implement.
  It didn't require us to deal with integers larger than 64 bits, so we could use the built in java ````long```` type.

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