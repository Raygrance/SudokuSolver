import java.io.File;
import java.util.Scanner;

public class SudokuSolver {

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(FileUtils.getSudokuFileName());
        System.out.println(sudoku);
    }
}