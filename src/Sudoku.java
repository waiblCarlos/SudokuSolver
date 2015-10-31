

public class Sudoku
{

	public static void main (String[] args)
	{
		// Default empty puzzle
		createBoard();
		int[] position = {0,0};
		int[][] puzzle = new int[9][9];
		solvePuzzle (puzzle, position);
	}

	// Will use the backtracking algorithm to fill in the puzzle
	public static boolean solvePuzzle(int[][] puzzle, int[] currentPosition)
	{
		// First checks if the grid is empty
		boolean inRow, inColumn, inZone, x;
		if (isGridEmpty(puzzle, currentPosition)) {
			for (int i = 1; i < 10; i++) {
				// Check no numbers exists in same rows & same columns same as (i) & 3x3 zone (i) is currently in.
				// If not then fill in that grid
				inRow = isInSameRow (puzzle, currentPosition, i);
				inColumn = isInSameColumn (puzzle, currentPosition, i);
				inZone = isInSameZone (puzzle, currentPosition, i);
				if (!inRow && !inColumn && !inZone) {
					puzzle[currentPosition[1]][currentPosition[0]] = i;
					currentPosition [0]++;
					if (currentPosition[0] == 9) {
						currentPosition [0] = 0;
						currentPosition [1]++;
						if (currentPosition[1] == 9) {
							printSolution(puzzle);
							return false;
						}
					}
					x = solvePuzzle(puzzle, currentPosition);
					if (!x) {
						return false;
					}
				}
			}
			puzzle[currentPosition[1]][currentPosition[0]] = 0;
			currentPosition[0]--;
			if (currentPosition[0] == -1) {
				currentPosition[0] = 8;
				currentPosition[1]--;
			}
		} else {
			currentPosition[0]++;
		}
		return true;
	}

	// Checks if the current grid is filled
	public static boolean isGridEmpty (int[][] puzzle, int[] position)
	{
		if (puzzle[position[1]][position[0]] == 0) {
			return true;
		}
		return false;
	}

	// Checks if the guess exists in the current row
	public static boolean isInSameRow (int[][] puzzle, int[] position, int guess)
	{
		for (int i = 0; i < 9; i++) {
			if (puzzle[position[1]][i] == guess) {
				return true;
			}
		}
		return false;
	}

	// Checks if the guess exists in the current column
	public static boolean isInSameColumn (int[][] puzzle, int[] position, int guess)
	{
		for (int i = 0; i < 9; i++) {
			if (puzzle[i][position[0]] == guess) {
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
				if (puzzle[3 * yZone + i][3 * xZone + j] == guess) {
					return true;
				}
			}
		}
		return false;
	}

	public static void printSolution(int[][] puzzle)
	{
		printRowLine();
		for (int i = 0; i < 9; i++) {
			printRowNumbers(puzzle, i);
			if (i % 3 == 2) {
				printRowLine();
			}
		}
		System.out.println();
	}

	public static void printRowLine() {
		for (int i = 0; i < 39; i++) {
			if (i % 13 == 0 && i != 0) {
				System.out.print("\t\t ");
			}
			System.out.print("-");
		}
	}

	public static void printRowNumbers(int[][] puzzle, int row) {
		System.out.print("\n| ");
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 2 && i != 8) {
				System.out.print(puzzle[row][i] + " | \t\t | ");
			} else {
				System.out.print(puzzle[row][i] + " | ");
			}
		}
		System.out.print("\n");
	}

	public static void createBoard() {

	}

}
