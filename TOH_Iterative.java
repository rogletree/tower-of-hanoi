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
	
	/* Method to create a stack object. Takes in an int parameter that sets that capacity of
	 * the stack. The "top" is initialized to -1 by default. An array is created as well,
	 * then the newly created stack object is returned.
	 */
	Stack createStack(int capacity) {
		Stack stack = new Stack();
		stack.capacity = capacity;
		stack.top = -1;
		stack.array = new int[capacity];
		return stack;
	}
	
	// Main method
	public static void main(String[] args) {
		
		// Asks the user how many disks they would like simulate with.
		Scanner scan = new Scanner(System.in);
		int numDisks;
		System.out.println("Input number of disks: ");
		numDisks = scan.nextInt();
		
		/* Asks the user how many test runs they would like to simulate. This
		 * was done for comparison purposes and getting the average time that
		 * the program takes to complete.
		 */
		System.out.println("Input number of test runs: ");
		int runs = scan.nextInt();
		
		// Initialized array that will hold the times for how long the program takes to run.
		double arr[] = new double[runs];
		
		// For loop to run the simulation however many times the user indicated.
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
			
			/* An end time is calculated. Minutes and seconds are calculated for
			 * but ultimately not used when printing out the total run time. For
			 * the lowest n values, the run time is negligible, but once it gets
			 * closer to n=20, the difference starts to become more apparent.
			 */
			double endTime = System.currentTimeMillis();
			double totalTime = endTime - startTime;
			double minutes = (totalTime / 1000) / 60;
			double seconds = (totalTime / 1000) % 60;
			
			arr[i] = totalTime;
			
			System.out.println("Run " + (i+1) + " run time = " + totalTime + " milliseconds.");
		}
		
		/* Calculates the average run times for however many execution runs there were.
		 * You may notice that I started it at i=1 so the first value in the array
		 * is not accounted for. This is because I noticed that for some reason,
		 * the first run time would sometimes be much different than the other runs
		 * times, so I decided to remove that from the average to not skew the results.
		 */
		double average = 0;
		for(int i = 1; i < arr.length; i++) {
			average = average + arr[i];
		}
		
		average = (average / (arr.length - 1));
		System.out.println("Average run time = " + average + " milliseconds.");
	}
	
	/* Boolean function to check if the stack being passed is at
	 * max capacity. If it's at max capacity, we don't want to
	 * add another disk on top.
	 */
	boolean isFull(Stack stack) {
		if (stack.top == stack.capacity - 1)
			return true;
		else
			return false;
	}
	
	/* Boolean function to check if the stack being passed is empty.
	 * If so, we don't want to try to remove a disk from it. This is
	 * where the stack's "top" being initialized at -1 comes into
	 * play. If when we check the "top" we see it's -1, we know the
	 * stack is empty.
	 */
	boolean isEmpty(Stack stack) {
		if (stack.top == -1)
			return true;
		else
			return false;
	}
	
	/* Function that adds a new disk on top of a stack. Takes in a stack
	 * object and an item (the disk) as parameters, checks if the stack
	 * is at max capacity, and if not, adds the disk on top.
	 */
	void push(Stack stack, int item) {
		if (isFull(stack))
			return;
		stack.array[++stack.top] = item;
	}
	
	/* Removes the top-most disk from a stack, taken as a parameter. First
	 * checks to see if the stack is currently empty, and only if not is
	 * the disk removed.
	 */
	int pop(Stack stack) {
		if(isEmpty(stack))
			return Integer.MIN_VALUE;
		else
			return stack.array[stack.top--];
	}
	
	/* This is where the magic happens, so to speak. Where the actual movement
	 * of the disks between poles takes place. This function takes in a "source"
	 * stack and a "destination" stack (these are not necessarily referring to
	 * the left-most and right-most poles of the overall game, just the starting
	 * and ending poles for a specific disk movement). The function also takes
	 * in characters "s" and "d" (standing for "source" and "destination") as
	 * parameters. This is for the purpose of printing out the movements to the
	 * console if the user wishes.
	 * 
	 * As you can see, the "moveDisk" lines are commented out. This is because
	 * for anything higher than the smallest of n values, the console becomes
	 * very cluttered very quickly due to the number of calculations being
	 * (2^n)-1.
	 */
	void moveDisksBetweenPoles(Stack source, Stack destination, char s, char d) {
		int pole1TopDisk = pop(source);
		int pole2TopDisk = pop(destination);
		
		if (pole1TopDisk == Integer.MIN_VALUE) {
			push(source, pole2TopDisk);
			//moveDisk(d,s, pole2TopDisk);
		}
		
		else if(pole2TopDisk == Integer.MIN_VALUE) {
			push(destination, pole1TopDisk);
			//moveDisk(s, d, pole1TopDisk);
		}
		
		else if(pole1TopDisk > pole2TopDisk) {
			push(source, pole1TopDisk);
			push(source, pole2TopDisk);
			//moveDisk(d, s,pole2TopDisk);
		}
		
		else {
			push(destination, pole2TopDisk);
			push(destination, pole1TopDisk);
			//moveDisk(s, d, pole1TopDisk);
		}
	}
	
	// Prints out which number disk is moving to/from which poles
	void moveDisk(char fromPeg, char toPeg, int disk) {
		System.out.println("Moving disk " + disk + " from " + fromPeg + " to " + toPeg);
	}
	
	/* Method to iteratively run the game and decide how to move the disks between
	 * the poles. The least number of moves to solve the Tower of Hanoi is calculated
	 * by (2^n)-1, which is how many moves it takes for this program to complete it.
	 * It calculates how many moves that is, pushes that many disks onto the "source"
	 * pole, then runs through the number of moves again, this time deciding
	 * which poles to move the disks between.
	 */
	void iterative(int numDisks, Stack source, Stack middle, Stack destination) {
		
		int totalNumMoves;
		char s = 'S';
		char m = 'M';
		char d = 'D';

		if(numDisks % 2 == 0) {
			char temp = d;
			d = m;
			m = temp;
		}
		
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
