# Code Challenges
My solutions to online coding katas from codewars that I found worth doing. My favorite one was the elevator problem!

## Battleship field validator
**Description:**

Write a method that takes a field for well-known board game "Battleship" as an argument and returns true if it has a valid disposition of ships, false otherwise. Argument is guaranteed to be 10*10 two-dimension array. Elements in the array are numbers, 0 if the cell is free and 1 if occupied by ship.

Battleship (also Battleships or Sea Battle) is a guessing game for two players. Each player has a 10x10 grid containing several "ships" and objective is to destroy enemy's forces by targetting individual cells on his field. The ship occupies one or more cells in the grid. Size and number of ships may differ from version to version. In this kata we will use Soviet/Russian version of the game.

Before the game begins, players set up the board and place the ships accordingly to the following rules:
* There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines (size 1). Any additional ships are not allowed, as well as missing ships.
* Each ship must be a straight line, except for submarines, which are just single cell.
* The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.

## Binomial expansion

**Description:**

Write a function `expand` that takes in an expression with a single, one character variable, and expands it. The expression is in the form `(ax+b)^n` where `a` and `b` are integers which may be positive or negative, `x` is any single character variable, and `n` is a natural number. If a = 1, no coefficient will be placed in front of the variable. If a = -1, a "-" will be placed in front of the variable.

The expanded form should be returned as a string in the form `ax^b+cx^d+ex^f`... where `a`, `c`, and `e` are the coefficients of the term, `x` is the original one character variable that was passed in the original expression and `b`, `d`, and `f`, are the powers that `x` is being raised to in each term and are in decreasing order.

If the coefficient of a term is zero, the term should not be included. If the coefficient of a term is one, the coefficient should not be included. If the coefficient of a term is -1, only the "-" should be included. If the power of the term is 0, only the coefficient should be included. If the power of the term is 1, the caret and power should be excluded.

## Conway's Game of Life - Unlimited Edition

**Description:**

Given a 2D array and a number of generations, compute n timesteps of Conway's Game of Life.

The rules of the game are:

1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
2. Any live cell with more than three live neighbours dies, as if by overcrowding.
3. Any live cell with two or three live neighbours lives on to the next generation.
4. Any dead cell with exactly three live neighbours becomes a live cell.
   
Each cell's neighborhood is the 8 cells immediately around it (i.e. Moore Neighborhood). The universe is infinite in both the x and y dimensions and all cells are initially dead - except for those specified in the arguments. The return value should be a 2d array cropped around all of the living cells. (If there are no living cells, then return `[[]]`.)

## The Lift (Elevator)

**Description:**

A multi-floor building has a Lift in it.

People are queued on different floors waiting for the Lift.

Some people want to go up. Some people want to go down.

The floor they want to go to is represented by a number (i.e. when they enter the Lift this is the button they will press)

```
BEFORE (people waiting in queues)               AFTER (people at their destinations)
                   +--+                                          +--+ 
  /----------------|  |----------------\        /----------------|  |----------------\
10|                |  | 1,4,3,2        |      10|             10 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 9|                |  | 1,10,2         |       9|                |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 8|                |  |                |       8|                |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 7|                |  | 3,6,4,5,6      |       7|                |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 6|                |  |                |       6|          6,6,6 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 5|                |  |                |       5|            5,5 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 4|                |  | 0,0,0          |       4|          4,4,4 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 3|                |  |                |       3|            3,3 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 2|                |  | 4              |       2|          2,2,2 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 1|                |  | 6,5,2          |       1|            1,1 |  |                |
  |----------------|  |----------------|        |----------------|  |----------------|
 G|                |  |                |       G|          0,0,0 |  |                |
  |====================================|        |====================================|
```

**Rules:**

Lift:
* The Lift only goes up or down!
* Each floor has both UP and DOWN Lift-call buttons (except top and ground floors which have only DOWN and UP respectively)
* The Lift never changes direction until there are no more people wanting to get on/off in the direction it is already travelling
* When empty the Lift tries to be smart. For example,
* If it was going up then it may continue up to collect the highest floor person wanting to go down
* If it was going down then it may continue down to collect the lowest floor person wanting to go up
* The Lift has a maximum capacity of people
* When called, the Lift will stop at a floor even if it is full, although unless somebody gets off nobody else can get on!
* If the lift is empty, and no people are waiting, then it will return to the ground floor

People:
* People are in "queues" that represent their order of arrival to wait for the Lift
* All people can press the UP/DOWN Lift-call buttons
* Only people going the same direction as the Lift may enter it
* Entry is according to the "queue" order, but those unable to enter do not block those behind them that can
* If a person is unable to enter a full Lift, they will press the UP/DOWN Lift-call button again after it has departed without them

**Kata task:**
* Get all the people to the floors they want to go to while obeying the Lift rules and the People rules
* Return a list of all floors that the Lift stopped at (in the order visited!)
NOTE: The Lift always starts on the ground floor (and people waiting on the ground floor may enter immediately)

**I/O**
Input:
* `queues` a list of queues of people for all floors of the building.
* The height of the building varies
* 0 = the ground floor
* Not all floors have queues
* Queue index `[0]` is the "head" of the queue
* Numbers indicate which floor the person wants go to
* `capacity` maximum number of people allowed in the lift

Output:
* A list of all floors that the Lift stopped at (in the order visited!)

## Proper fractions

**Description:**

If n is the numerator and d the denominator of a fraction, that fraction is defined a (reduced) proper fraction if and only if GCD(n,d)==1.

For example `5/16` is a proper fraction, while `6/16` is not, as both 6 and 16 are divisible by 2, thus the fraction can be reduced to `3/8`.

Now, if you consider a given number d, how many proper fractions can be built using d as a denominator?

For example, let's assume that d is 15: you can build a total of 8 different proper fractions between 0 and 1 with it: 1/15, 2/15, 4/15, 7/15, 8/15, 11/15, 13/15 and 14/15.

You are to build a function that computes how many proper fractions you can build with a given denominator:
```
proper_fractions(1)==0
proper_fractions(2)==1
proper_fractions(5)==4
proper_fractions(15)==8
proper_fractions(25)==20
```
Be ready to handle big numbers.

Edit: to be extra precise, the term should be "reduced" fractions.

## How many are smaller than me II?
I found this problem to be straightforward to understand but difficult to solve as it is also an optimization problem.

**Description:**

Given an array `arr`, you have to return the amount of numbers that are smaller than `arr[i]` to the right.

For example:
```
smaller([5, 4, 3, 2, 1]) === [4, 3, 2, 1, 0]
smaller([1, 2, 0]) === [1, 1, 0]
```

## Most frequently used words in a text

**Description:**

Write a function that, given a string of text (possibly with punctuation and line-breaks), returns an array of the top-3 most occurring words, in descending order of the number of occurrences.

Assumptions:
* A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
* Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
* Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
* Matches should be case-insensitive, and the words in the result should be lowercased.
* Ties may be broken arbitrarily.
* If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned, or an empty array if a text contains no words.

Examples:
```
"In a village of La Mancha, the name of which I have no desire to call to
mind, there lived not long since one of those gentlemen that keep a lance
in the lance-rack, an old buckler, a lean hack, and a greyhound for
coursing. An olla of rather more beef than mutton, a salad on most
nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
on Sundays, made away with three-quarters of his income."

--> ["a", "of", "on"]


"e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"

--> ["e", "ddd", "aa"]


"  //wont won't won't"

--> ["won't", "wont"]
```
