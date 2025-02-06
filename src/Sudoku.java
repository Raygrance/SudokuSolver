import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Sudoku {

    private ArrayList<ArrayList<Tile>> sudoku = new ArrayList<>();
    private static int size = 0;

    public Sudoku(String fileName) {

        try {
            parsePuzzle(fileName);
        } catch (IOException e) {
            System.out.println("Enter valid file name");
        }
    }

    /**
     * parses the incoming sudoku, recording the location, number, given, and possible numbers for non given numbers
     * @param fileName
     * @throws IOException
     */
    public void parsePuzzle(String fileName) throws IOException {
        // opens file to be read
        File sudokuFile = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(sudokuFile));

        // reads in one line at a time
        String line;
        while ((line = bufferedReader.readLine()) != null) {

            ArrayList<Tile> puzzleLine = new ArrayList<>();

            for (int i = 0; i < line.length(); i = i + 2) {

                // creates a new tile for each spot and parses information
                Tile tile = new Tile(i/2, size, Character.getNumericValue(line.charAt(i)));
                puzzleLine.add(i/2, tile);
            }
            sudoku.add(size, puzzleLine);
            size++;
        }
        bufferedReader.close();

        // sets all possible numbers for all non given tiles
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // checks if number is given
                if (!sudoku.get(i).get(j).isGiven()) {
                    sudoku.get(i).get(j).setPossibleNumbers(size);
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < 2 * size + 1; i++) {
            // prints border row
            if (i % 2 == 0) {
                for (int j = 0; j < size; j++) {
                    output += "+ ─── ";
                }
                output += "+\n";
            } else {
                // prints number row
                for (int j = 0; j < size; j++) {
                    // replaces 0 with blank space
                    if (sudoku.get(i / 2).get(j).getNum() == 0) {
                        output += "|     ";
                    } else {
                        output += "|  " + sudoku.get(i / 2).get(j).getNum() + "  ";
                    }
                }
                output += "|\n";
            }
        }

        return output;
    }

    public static int getSize() {
        return size;
    }

    public boolean checkRow(int rowIndex) {
        HashSet<Integer> seenNums = new HashSet<>();
        
        for (int i = 0; i < sudoku.get(rowIndex).size(); i++) {
            int num = sudoku.get(rowIndex).get(i).getNum();

            if (num != 0) {
                if (seenNums.contains(num)) {
                    return false;
                } else {
                    seenNums.add(num);
                }
            }
        }
        return true;
    }

    public boolean checkCol(int colIndex) {
        HashSet<Integer> seenNums = new HashSet<>();
        
        for (int i = 0; i < sudoku.size(); i++) {
            int num = sudoku.get(i).get(colIndex).getNum();

            if (num != 0) {
                if (seenNums.contains(num)) {
                    return false;
                } else {
                    seenNums.add(num);
                }
            }
        }

        return true;
    }

    /**
     * Checks if the 3x3 grid according to sudoku rules is valid
     * @param location
     * @return
     */
    public boolean checkGrd(Point location) {
        HashSet<Integer> seenNums = new HashSet<>();
    
        // Calculate the starting row and column for the 3x3 grid
        int startRow = (location.getY() / 3) * 3;
        int startCol = (location.getX() / 3) * 3;
    
        // Loop through the 3x3 grid
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                // Ensure we're only checking non-zero numbers
                if (sudoku.get(i).get(j).getNum() != 0) {
                    int num = sudoku.get(i).get(j).getNum();
                    if (seenNums.contains(num)) {
                        return false;  // Duplicate number found
                    } else {
                        seenNums.add(num);  // Add number to the set
                    }
                }
            }
        }
        return true;  // No duplicates found
    }

    public ArrayList<ArrayList<Tile>> getSudoku() {
        return sudoku;
    }
}
