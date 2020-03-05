import java.util.Scanner;

/* Recursive solution to the Tower of Hanoi game. This solution
 * is much cleaner code than the iterative solution, and it also
 * runs much faster.
 */
public class TOH_recursive {
	
	public static void main(String[] args) {
		
		// Asks the user how many disks they want to use.
		Scanner scan = new Scanner(System.in);
		System.out.print("Input number of disks: ");
		int numDisks = scan.nextInt();
		
		// Asks the user how many test runs they want to execute.
		System.out.print("Input number of test runs: ");
		int runs = scan.nextInt();
		
		/* Characters "s", "d", and "m" standing for "source",
		 * "destination", and "middle".
		 */
		char s = 'S';
		char d = 'D';
		char m = 'M';
		
		// Array created the will hold the run execution times.
		double arr[] = new double[runs];
		
		// For loop that runs through however many runs the user indicated.
		for(int i = 0; i < runs; i++) {
			
			// Starts the clock for seeing how long it takes the program to execute.
			double startTime = System.currentTimeMillis();
			
			// Calls the function to run the game.
			towerOfHanoi(numDisks, s, d, m);
		
			double endTime = System.currentTimeMillis();
			double totalTime = endTime - startTime;
			double minutes = (totalTime / 1000) / 60;
			double seconds = (totalTime / 1000) % 60;
			
			// Adds the run time to the prior created array.
			arr[i] = totalTime;
		
			System.out.println("Run " + (i+1) + " run time = " + totalTime + " milliseconds.");
			
		}
		double average = 0;
		
		/* Calculates the average run time for the runs. I disregarded the
		 * time for the first run because sometimes it was really different
		 * than the other run times and I didn't want it to skew the results.
		 */
		for(int i = 1; i < arr.length; i++) {
			average = average + arr[i];
		}
		average = (average / (arr.length - 1));
		System.out.println("Average run time = " + average + " milliseconds.");
		
	}

	/* Recursive function that plays the Tower of Hanoi game. Takes in parameters
	 * "n" which is the number of disks, "src_rod" which is the left-most pole and
	 * where all the disks start out, "dest_rod" which is the right-most pole and
	 * where all the disks will eventually end up, and "mid_rod" which is the
	 * middle pole.
	 * 
	 * The print statements stating which disk is being moved between the poles are
	 * commented out because on any n values higher than the very smallest, the
	 * console becomes cluttered very quickly.
	 */
	static void towerOfHanoi(int n, char src_rod, char dest_rod, char mid_rod) {
		/* When n=1, that means there is only 1 disk left to move, and that is
		 * from either the left-most pole to the right-most pole or from the
		 * middle pole to the right-most pole. Could be either one depending on
		 * how many disks there are.
		 */
		if (n == 1) {
			//System.out.println("Move disk 1 from rod " + src_rod + " to rod " + dest_rod);
			return;
		}
		towerOfHanoi(n-1, src_rod, mid_rod, dest_rod);
		//System.out.println("Move disk " + n + " from rod " + src_rod + " to rod " + dest_rod);
		towerOfHanoi(n-1, mid_rod, dest_rod, src_rod);
	}
	
}
