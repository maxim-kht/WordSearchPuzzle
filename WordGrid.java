/*----------------------------------------------------------------
 *  Author:        Maxim Kukhtenkov
 *  Written:       10/5/2014
 *  Last updated:  10/7/2014
 *  
 *  WordGrid Class for a Word Search Puzzle. Used to create a
 *  word grid object.
 *
 *----------------------------------------------------------------*/

public class WordGrid {
	private int maxRowCount;	
	private int maxColCount;
	private Letter letters[][];
	

	WordGrid(int maxRowCount, int maxColCount) { 
		letters = new Letter[maxRowCount][maxColCount];

		for (int y = 0; y < maxRowCount; y++) {
			for (int x = 0; x < maxColCount; x++) {
				int random = (int) (Math.random() * 26);
				letters[y][x] = new Letter((char) ('A' + random), y, x);
			}
		}
		
		this.maxRowCount = maxRowCount;
		this.maxColCount = maxColCount;	
	}
	
	
	private class Letter {
		char value;
		int yCoordinate;
		int xCoordinate;
		Letter[] neighbors;
		
		// Initializes value of the letter and declares the neighbor array
		Letter(char value, int yCoordinate, int xCoordinate) {
			this.yCoordinate = yCoordinate;
			this.xCoordinate = xCoordinate;
			this.value = value;
			neighbors = new Letter[8];
		}
	}

	void displayGrid() {
		System.out.println("-----------------\n   Word Puzzle   \n-----------------\n");
		String colCount = " \t";
		for (int y = 1; y < maxColCount + 1; y++){
			colCount += y;
			colCount += " ";
		}
		System.out.println(colCount + "\n");
		for (int y = 1; y < maxRowCount + 1; y++) {
			System.out.print(y + "\t");
			for (int x = 0; x < maxColCount; x++) {
				System.out.print(letters[y-1][x].value + " ");
			}
			System.out.println();
		}
	}
	
	public void searchWord(String word) {
		int startY = 0;
		int startX = 0;
		int endY = 0;
		int endX = 0;
		
		boolean wordFound = false;
		
		for (int y = 0; y < maxRowCount && !wordFound; y++) {
			for (int x = 0; x < maxColCount && !wordFound; x++) {
				// If first letter is found
				if (word.charAt(0) == letters[y][x].value) {
					startY = y;
					startX = x;
					
					// Search all neighbors
					for (int i = 0; i < 8 && !wordFound; i++) {
						boolean nextLetterFound = true;
						int count = 1;
						Letter gridLetter = letters[y][x].neighbors[i];
					
						while(count < word.length() && nextLetterFound && !wordFound && gridLetter != null) {
							if (word.charAt(count) != gridLetter.value) {
								nextLetterFound = false;
							}
							else if (count == word.length() - 1 && word.charAt(count) == gridLetter.value) {
								wordFound = true;
								endY = gridLetter.yCoordinate;
								endX = gridLetter.xCoordinate;
							}
							else {
								count++;
								gridLetter = gridLetter.neighbors[i];
							}
						}
					} // End-search all neighbors
				} // End-if first letter is found
			}
		}
				
		
		if (wordFound) {
			System.out.println("(" + (startX +1) + ", " + (startY +1) + "), (" + (endX+1) + ", " + (endY+1) + ")");
		} 
		else {
			System.out.println("Not Found");
		}
	}
	
	
	
	public void initializeNeighbors(boolean wrap) {
		for (int y = 0; y < maxRowCount; y++) {
			for (int x = 0; x < maxColCount; x++) {
				populateNeighbors(y, x, wrap);
			}
		}
	}
	

	private void populateNeighbors(int y, int x, boolean wrap) {
		int maxRow = maxRowCount - 1;
		int maxCol = maxColCount - 1;

		boolean northLimit = (y == 0);
		boolean southLimit = (y == maxRow);
		boolean westLimit = (x == 0);
		boolean eastLimit = (x == maxCol);

		// North-West
		if (!northLimit && !westLimit) 
			letters[y][x].neighbors[0] = letters[y - 1][x - 1];
		else if (!wrap)
			letters[y][x].neighbors[0] = null; 
		else if (northLimit && westLimit)
			letters[y][x].neighbors[0] = letters[maxRow][maxCol];
		else if (northLimit)
			letters[y][x].neighbors[0] = letters[maxRow][x - 1]; 
		else if (westLimit)
			letters[y][x].neighbors[0] = letters[y - 1][maxCol];


		// North
		if (!northLimit)
			letters[y][x].neighbors[1] = letters[y - 1][x];
		else if (!wrap)
			letters[y][x].neighbors[1] = null;
		else if (northLimit)
			letters[y][x].neighbors[1] = letters[maxRow][x];


		//North-East
		if (!northLimit && !eastLimit)
			letters[y][x].neighbors[2] = letters[y - 1][x + 1];
		else if (!wrap)
			letters[y][x].neighbors[2] = null;
		else if (northLimit && eastLimit)
			letters[y][x].neighbors[2] = letters[maxRow][0];
		else if (northLimit)
			letters[y][x].neighbors[2] = letters[maxRow][x + 1];
		else if (eastLimit)
			letters[y][x].neighbors[2] = letters[y - 1][0];


		//East
		if (!eastLimit)
			letters[y][x].neighbors[3] = letters[y][x + 1];
		else if (!wrap)
			letters[y][x].neighbors[3] = null;
		else if (eastLimit)
			letters[y][x].neighbors[3] = letters[y][0];


		//South-East
		if (!southLimit && !eastLimit)
			letters[y][x].neighbors[4] = letters[y + 1][x + 1];
		else if (!wrap)
			letters[y][x].neighbors[4] = null;
		else if (southLimit && eastLimit)
			letters[y][x].neighbors[4] = letters[0][0];
		else if (southLimit)
			letters[y][x].neighbors[4] = letters[0][x + 1];
		else if (eastLimit)
			letters[y][x].neighbors[4] = letters[y + 1][0];


		//South
		if (!southLimit)
			letters[y][x].neighbors[5] = letters[y + 1][x];
		else if (!wrap)
			letters[y][x].neighbors[5] = null;
		else if (southLimit)
			letters[y][x].neighbors[5] = letters[0][x];


		//South-West
		if (!southLimit && !westLimit)
			letters[y][x].neighbors[6] = letters[y + 1][x - 1];
		else if (!wrap)
			letters[y][x].neighbors[6] = null;
		else if (westLimit && southLimit)
			letters[y][x].neighbors[6] = letters[0][maxCol];
		else if (westLimit)
			letters[y][x].neighbors[6] = letters[y + 1][maxCol];
		else if (southLimit)
			letters[y][x].neighbors[6] = letters[0][x - 1];


		//West
		if (!westLimit)
			letters[y][x].neighbors[7] = letters[y][x - 1];
		else if (!wrap)
			letters[y][x].neighbors[7] = null;
		else if (westLimit)
			letters[y][x].neighbors[7] = letters[y][maxCol];
	}
}
