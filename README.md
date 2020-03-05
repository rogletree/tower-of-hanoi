# tower-of-hanoi
Recursive and iterative implementations of the Tower of Hanoi using Java

These are two Tower of Hanoi programs. One of them solves it iteratively, the other solves it recursively.

For both of the programs, it starts out by asking the user how many disks they would like to have, then asks them how many times they would like to run the game. The game runs, and for each run it times how long the program takes to execute, then stores those times in an array. After all the runs, it finds the average run time. However, it excludes the first run from this. For some reason, I found that sometimes the first run would be either noticeably slower or faster than the other runs so I disregarded it so as not to skew the results.

For n number of disks, the Tower of Hanoi requires a minimum of (2^n)-1 number of disk moves to complete the game. It has a time complexity of O(2^n). I took the average run times and created graphs of them (not uploaded here) and for the iterative implementation, the graph of the average run times almost perfectly mirrors that of 2^n, which serves to illustrate the time complexity of the program. The graph for the recursive implementation is not quite as clear-cut but the shape of the curve is still there.
