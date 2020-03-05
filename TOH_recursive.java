import java.util.Scanner;

/* Recursive solution to the Tower of Hanoi game. This solution
 * is much cleaner code than the iterative solution, and it also
 * runs much faster.
 */
public class TOH_recursive {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Input number of disks: ");
		int numDisks = scan.nextInt();
		
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

	static void towerOfHanoi(int n, char src_rod, char dest_rod, char mid_rod) {
		/* When n=1, that means there is only 1 disk left to move, and that is
		 * from either the left-most pole to the right-most pole or from the
		 * middle pole to the right-most pole. Could be either one depending on
		 * how many disks there are.
		 * 
		 * The print statements are commented out because for anything higher than
		 * the smallest n values, the console gets very cluttered.
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
