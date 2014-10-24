
Word Search Puzzle
------------------

Popular algorithms/programming exercise.

Word search puzzle that allows user to find the words in the word grid and outputs their coordinates in a following format: "(xStart, yStart), (xEnd, yEnd)".

The program prompts the user to enter the dimensions of the grid, which contains randomly generated letters and allows user to search specified amount of words in two different modes: wrap and non-wrap. 

In non-wrap mode the puzzle works like the regular word puzzle: only the words inside the grid are searched.

In a wrap mode if the word hits the border it continues on the other side of the grid. For example:

A B C        
D E F         
G H I          

If we chose the wrap mode and try to search for ABCAB the program will find the word and will output the following coordinates: (0, 0), (0, 1). 

The search is performed in all 8 directions.
