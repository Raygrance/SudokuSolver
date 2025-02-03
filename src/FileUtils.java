import java.util.Scanner;

public class FileUtils {

    public static String getSudokuFileName() {
        System.out.println("Enter sudoku.txt file name: ");
        Scanner scanner = new Scanner(System.in);

        return "sudokus/" + scanner.nextLine();
    }
}
