/*----------------------------------------------------------------
 *  Author:        Maxim Kukhtenkov
 *  Written:       10/5/2014
 *  Last updated:  10/5/2014
 *  
 *  Main Class for a Word Search Puzzle
 *
 *  Execution: % java Main
 *
 *----------------------------------------------------------------*/

public class Main {
	public static void main(String[] args) {
		
		System.out.println("\n-------------------\n" + 
				           "    Word Search    \n" + 
			               "-------------------\n\n");

		/*---------------------------------------------------------
		 * 1. Input the number of rows and columns 
		 *-------------------------------------------------------*/
		
		java.util.Scanner input = new java.util.Scanner(System.in);

		System.out.println("Enter the number of rows: ");
		int rows = input.nextInt();

		System.out.println("Enter the number of columns: ");
		int columns = input.nextInt();
		
		WordGrid grid = new WordGrid(rows, columns);
		grid.displayGrid();
		
		/*---------------------------------------------------------
		 *  2. Chose the wrap or no wrap mode 
		 *-------------------------------------------------------*/
		
		boolean wrap = false;
		
		System.out.println("Choose the mode: \n1. Wrap\n2. No wrap\n(Type 1 for wrap or 2 for no-wrap)\n");
		int choice = input.nextInt();
		
		while (choice < 1 || choice > 2) {
			System.out.println("Please type 1 for Wrap mode or 2 for No Wrap");
			choice = input.nextInt();
		}
		
		switch (choice) {
		case 1: wrap = true;
			System.out.println("You chose the WRAP mode\n");
			break;
		case 2: wrap = false;
			System.out.println("You chose the NO WRAP mode\n");
			break;
		}
		
		// Initialize the Letter neighbors based on the chosen mode
		grid.initializeNeighbors(wrap);
		
		
		/*---------------------------------------------------------
		 *  3. Insert words and search them in the grid 
		 *-------------------------------------------------------*/
		
		System.out.println("How many words do you wish to find?");
		int numOfWords = input.nextInt();
		input.nextLine(); // Consume newline left over
		
		String[] words = new String[numOfWords];
		
		System.out.println("Enter " + numOfWords + ((numOfWords > 1) ? " words: " : " word: "));
		for (int i = 0; i < words.length; i++) {
			words[i] = input.nextLine();
		}
		
		System.out.println("\n------------\n Output\n------------\n ");
		for (int i = 0; i < words.length; i++)
			grid.searchWord(words[i].toUpperCase());

		System.out.println("Thanks for playing!");
	}
}
