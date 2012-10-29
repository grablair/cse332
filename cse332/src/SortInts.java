import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Program for sorting an input file full of
 * integers.
 * 
 * @author Graham Arthur Blair
 */
public class SortInts {
	/**
	 * Sorts an input file of integers.
	 * 
	 * @param args Takes input and output filenames
	 */
	public static void main(String[] args) {
		if (args.length != 2)
			throw new IllegalArgumentException("Must have two args, " +
					"input file and output file");
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		
		Tree<Integer> tree = new Tree<Integer>();
		try {
			Scanner in = new Scanner(inputFile);
			while (in.hasNextInt())
				tree.insert(in.nextInt());
			in.close();
			
			PrintStream out = new PrintStream(outputFile);
			for (int i : tree.getSortedList())
				out.println(i);
			out.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Input file does not " +
					"exist");
		}
	}
}
