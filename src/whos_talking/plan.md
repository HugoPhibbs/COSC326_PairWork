# Look Who's Talking 

## Splitting the sentence
- Split into pronoun and verb phrase. This is a key part of the algorithm that makes the rest of the algorithm easy. 

## Vowel phrase stemming
- Need to find a way to find the stem of a vowel phrase.
- Store a list of vowel phrases, then map this to it's tense and the stem vowel. Eg "saw" -> "past", "see"

## Sentence rules
- Can have no more than 5 words, and no less than 2. 
- If it has 4 words, then it must use a specifier for the pronoun
- Sentences of length 2 or 3 are harder to deal with.

## Assumptions
- Assume that the inputted sentence is grammatically correct - eg "We is calling" should be translated despite it being incorrect

