import java.io.*;
import java.util.ArrayList;

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
}
