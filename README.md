# tower-of-hanoi
Recursive and iterative implementations of the Tower of Hanoi using Java

These are two Tower of Hanoi programs I made for my algorithm analysis class. One of them does the game iteratively, the other does it using recursion. Each program also prints out average run times for how long it takes to execute all the calculations.

For n number of disks, the Tower of Hanoi requires a minimum of (2^n)-1 number of disk moves to complete the game. It has a time complexity of O(2^n). I took the average run times and created graphs of them (not uploaded here) and for the iterative implementation, the graph of the average run times almost perfectly mirrors that of 2^n, which serves to illustrate the time complexity of the program. The graph for the recursive implementation is not quite as clear-cut but the shape of the curve is still there.
