import java.util.ArrayList;

public class Tile {

    private Point location;
    private int num;
    private boolean given;
    private ArrayList<Integer> possibleNumbers;
    private int score;

    /**
     * sets initial conditions for each tile
     * @param x
     * @param y
     * @param num
     */
    public Tile(int x, int y, int num) {
        location = new Point(x, y);
        given = num != 0;
        this.num = num;
    }

    /**
     * sets possibleNumbers to be an arraylist of numbers from 1 to allNums
     * @param allNums
     */
    public void setPossibleNumbers(int allNums) {
        possibleNumbers = new ArrayList<>();
        for (int i = 1; i <= allNums; i++) {
            possibleNumbers.add(i);
        }
    }

    public int getNum() {
        return num;
    }

    public boolean isGiven() {
        return given;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "location=(" + location.getX() + ", " + location.getY() +
                "), num=" + num +
                ", possibleNumbers=" + possibleNumbers +
                ", given=" + given +
                ", score=" + score +
                '}';
    }
}
