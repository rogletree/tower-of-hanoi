import java.util.Scanner;

// Iterative solution to the Tower of Hanoi game.
public class TOH_Iterative {
	
	/* Uses stack class to create objects which are where the disk movements will take place.
	 * "capacity" is the max capacity of disks that can be held on a stack.
	 * "top" refers to the top-most disk on the stack.
	 */
	class Stack{
		int capacity;
		int top;
		int array[];
	}
	
	Stack createStack(int capacity) {
		Stack stack = new Stack();
		stack.capacity = capacity;
		stack.top = -1;
		stack.array = new int[capacity];
		return stack;
	}
	
	// Main method
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int numDisks;
		System.out.println("Input number of disks: ");
		numDisks = scan.nextInt();
		
		System.out.println("Input number of test runs: ");
		int runs = scan.nextInt();
		
		// Array that will hold the run times for each run
		double arr[] = new double[runs];

		for(int i = 0; i < runs; i++) {
			
			// Starts the clock to time program execution (in milliseconds).
			double startTime = System.currentTimeMillis();
			
			/* "source", "destination", and "middle" towers are created using the
			 * number of disks indicated by the user.
			 */
			TOH_Iterative tower = new TOH_Iterative();
			Stack source;
			Stack destination;
			Stack middle;
			source = tower.createStack(numDisks);
			destination = tower.createStack(numDisks);
			middle = tower.createStack(numDisks);
		
			// Starts the actual game run.
			tower.iterative(numDisks,  source,  middle,  destination);
			
			
			double endTime = System.currentTimeMillis();
			double totalTime = endTime - startTime;
			double minutes = (totalTime / 1000) / 60;
			double seconds = (totalTime / 1000) % 60;
			
			arr[i] = totalTime;
			
			System.out.println("Run " + (i+1) + " run time = " + totalTime + " milliseconds.");
		}
		
		double average = 0;
		for(int i = 1; i < arr.length; i++) {
			average = average + arr[i];
		}
		
		average = (average / (arr.length - 1));
		System.out.println("Average run time = " + average + " milliseconds.");
	}
	
	// Checks if pole is at max capacity
	boolean isFull(Stack stack) {
		if (stack.top == stack.capacity - 1)
			return true;
		else
			return false;
	}
	
	// Checks if pole is empty
	boolean isEmpty(Stack stack) {
		if (stack.top == -1)
			return true;
		else
			return false;
	}
	
	/* Adds a disk on top of a pole. First checks whether pole is at
	 * max capacity, and if not, pushes disk to top.
	 */
	void push(Stack stack, int item) {
		if (isFull(stack))
			return;
		stack.array[++stack.top] = item;
	}
	
	/* Takes top disk off of pole. First checks whether pole is
	 * empty, and if not, takes the top disk off.
	 */
	int pop(Stack stack) {
		if(isEmpty(stack))
			return Integer.MIN_VALUE;
		else
			return stack.array[stack.top--];
	}
	

	/* Moves the disk between two poles. "source" and "destination" are not
	 * necessarily referring to the left-most and right-most original poles.
	 * "source" refers to the pole that the disk is being moved from, "destination"
	 * refers to the pole the disk is being moved to. Same for "s" and "d".
	 */
	void moveDisksBetweenPoles(Stack source, Stack destination, char s, char d) {
		int pole1TopDisk = pop(source);
		int pole2TopDisk = pop(destination);
		
		if (pole1TopDisk == Integer.MIN_VALUE) {
			push(source, pole2TopDisk);
			//printDiskMovement(d,s, pole2TopDisk);
		}
		
		else if(pole2TopDisk == Integer.MIN_VALUE) {
			push(destination, pole1TopDisk);
			//printDiskMovement(s, d, pole1TopDisk);
		}
		
		else if(pole1TopDisk > pole2TopDisk) {
			push(source, pole1TopDisk);
			push(source, pole2TopDisk);
			//printDiskMovement(d, s,pole2TopDisk);
		}
		
		else {
			push(destination, pole2TopDisk);
			push(destination, pole1TopDisk);
			//printDiskMovement(s, d, pole1TopDisk);
		}
	}
	
	// Prints out which number disk is moving to/from which poles
	void printDiskMovement(char fromPeg, char toPeg, int disk) {
		System.out.println("Moving disk " + disk + " from " + fromPeg + " to " + toPeg);
	}
	
	
	void iterative(int numDisks, Stack source, Stack middle, Stack destination) {
		
		int totalNumMoves;
		
		// "s" is source, "m" is middle, "d" is destination.
		char s = 'S';
		char m = 'M';
		char d = 'D';

		if(numDisks % 2 == 0) {
			char temp = d;
			d = m;
			m = temp;
		}
		
		// Minimum number of moves required to solve the game is (2^n)-1
		totalNumMoves = (int)(Math.pow(2, numDisks) - 1);
		
		// Fills the "source" stack with all the disks
		for(int i = numDisks; i >= 1; i--) {
			push(source, i);
		}
		
		/* Runs through a "for" loop for the total number of moves it will take
		 * to solve the game. Uses modular operation to decide which disk needs
		 * to be moved.
		 */
		for(int i = 1; i <= totalNumMoves; i++) {
			if(i % 3 == 1)
				moveDisksBetweenPoles(source,destination, s, d);
			else if(i % 3 == 2)
				moveDisksBetweenPoles(source, middle, s, m);
			else if(i % 3 == 0)
				moveDisksBetweenPoles(middle, destination, m, d);
		}
	}
}
