import java.io.File;
import java.util.Scanner;

public class SudokuSolver {

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(FileUtils.getSudokuFileName());
        System.out.println(sudoku);

        for (int i = 0; i < sudoku.getSize(); i++) {
            for (int j = 0; j < sudoku.getSize(); j++) {
                System.out.println("Point at row " + i + ", column " + j + " has valid row: " + sudoku.checkRow(i) + ", has valid column: " + sudoku.checkCol(j) + " has valid grid: " + sudoku.checkGrd(sudoku.getSudoku().get(i).get(j).getLocation()));
                
            }
        }
    }
}