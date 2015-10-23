

public class Sudoku
{

	public static void main (String[] args)
	{
		// Default empty puzzle
		int[] position = {0,0};
		int[][] puzzle = new int[9] [9];
		solvePuzzle (puzzle, position);
	}

	// Will use the backtracking algorithm to fill in the puzzle
	public static void solvePuzzle (int[][] puzzle, int[] currentPosition)
	{
		// First checks if the grid is empty
		boolean inRow, inColumn, inZone;
		if (isGridEmpty (puzzle, currentPosition)) {
			for (int i = 1; i<10; i++) {
				// Check no numbers exists in same rows & same columns same as (i) & 3x3 zone (i) is currently in.
				// If not then fill in that grid
				inRow = isInSameRow (puzzle, currentPosition, i);
				inColumn = isInSameColumn (puzzle, currentPosition, i);
				inZone = isInSameZone (puzzle, currentPosition, i);
				if (!inRow && !inColumn && !inZone) {
					puzzle[currentPosition[0]][currentPosition[1]] = i;
					currentPosition [0]++;
					if (currentPosition [0] == 9) {
						currentPosition [0] = 0;
						currentPosition [1]++;
						if (currentPosition [1] == 9) {
							printSolution ();
						}
					}
					solvePuzzle (puzzle, currentPosition);
				}
				if (i == 9) {
					return;
				}
			}
		} 
	}

	// Checks if the current grid is filled
	public static boolean isGridEmpty (int[][] puzzle, int[] position)
	{
		if (puzzle[position[0]][position[1]] == 0) {
			return true;
		}
		return false;
	}

	// Checks if the guess exists in the current row
	public static boolean isInSameRow (int[][] puzzle, int[] position, int guess)
	{
		for (int i=0; i<9; i++) {
			if (puzzle[i][position[1]] == guess) {
				return true;
			}
		}
		return false;
	}

	// Checks if the guess exists in the current column
	public static boolean isInSameColumn (int[][] puzzle, int[] position, int guess)
	{
		for (int i=0; i<9; i++) {
			if (puzzle[position[0]][i] == guess) {
				return true;
			}
		}
		return false;
	}

	// Checks if the guess exists in the current 3X3 zone
	public static boolean isInSameZone (int[][] puzzle, int[] position, int guess)
	{
		int xZone = (int) Math.floor(position[0] / 3);
		int yZone = (int) Math.floor(position[1] / 3);
		for (int i =0; i<3; i++) {
			for (int j =0; j<3; j++) {
				if (puzzle[3 * xZone + i][3 * yZone + j] == guess) {
					return true;
				}
			}
		}
		return false;
	}

	public static void printSolution ()
	{

	}

}
