import java.util.Objects;

public class Board {
    /*
    This class generates a squire game board for chess games.
    Function of each method is explained above it.
    */
    private Cell [][] cellList;
    private int length;

    public Board(){
        cellList = new Cell[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++)
            cellList[i][j] = new Cell();
        }
    }

    public Board(int n){
        cellList = new Cell[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++)
                cellList[i][j] = new Cell(String.valueOf(i*n+j+1));     //For each cell, initialize its symbol to be its position in the board.
        }
        length = n;
    }

    /*
    Returns the number of cells in the board.
    */
    public int get_size(){
        return this.length*this.length;
    }

    /*
    Returns the symbol of the cell specified by its position.
    */
    public String get_value(int p){
        return this.cellList[(p-1)/length][(p-1)%length].get_value();
    }

    /*
    Change the symbol of the cell specified by its position.
    */
    public void set_value(int p, String v){
        this.cellList[(p-1)/length][(p-1)%length].set_value(v);
    }

    /*
    Print out the board in a square structure as well as the symbol of each sell.
    */
    public void print_board() {
        for (int i = 0; i < length; i++){
            System.out.print("+-----");
        }
        System.out.println("+");

        String val;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                val = cellList[i][j].get_value();
                // Change the space according to the length of the cell's symbol.
                if (val.length() == 1){
                    System.out.print("|  " + cellList[i][j].get_value() + "  ");
                }
                else if (val.length() == 2){
                    System.out.print("| " + cellList[i][j].get_value() + "  ");
                }
                else if (val.length() == 3){
                    System.out.print("| " + cellList[i][j].get_value() + " ");
                }

            }
            System.out.println("|");
            for (int j = 0; j < length; j++){
                System.out.print("+-----");
            }
            System.out.println("+");
        }
    }

    /*
    Reset the symbol of each cell to its position.
    */
    public void clean_board(){
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++)
                cellList[i][j].set_value(String.valueOf(i*length+j+1));
        }
    }

    /*
    Regenerate the board by given size.
    */
    public void change_board(int n){
        cellList = new Cell[n][n];
        length = n;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                cellList[i][j] = new Cell(String.valueOf(i * n + j + 1));
            }
        }
    }

    /*
    Check whether the board has a streak of cells that has the same symbol. The streak can be vertical,
    horizontal or canted.
    */
    public boolean check_streak(int n, int p){
        //Calculate the exact coordinates of the cell by given position.
        int row = (p-1)/length;
        int col = (p-1)%length;
        String symbol = cellList[row][col].get_value();
        for (int i = 0; i < 4; i++) {
            /*
            Check four directions(vertical line, horizontal line and two diagonals). Move from
            the current coordinate along the line, calculate the number of same cells on both sides.
            If the number of same cells matches the number n, return ture, else return false.
            */
            int upperStreak = 0;
            int lowerStreak = 0;
            switch (i) {
                case 0 -> {
                    for (int j = 1; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row + j][col].get_value(), symbol)) {
                                upperStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    for (int j = 0; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row - j][col].get_value(), symbol)) {
                                lowerStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    if (lowerStreak + upperStreak == n) {
                        return true;
                    }
                }
                case 1 -> {
                    for (int j = 1; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row][col + j].get_value(), symbol)) {
                                upperStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    for (int j = 0; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row][col - j].get_value(), symbol)) {
                                lowerStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    if (lowerStreak + upperStreak == n) {
                        return true;
                    }
                }
                case 2 -> {
                    for (int j = 1; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row + j][col + j].get_value(), symbol)) {
                                upperStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    for (int j = 0; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row - j][col - j].get_value(), symbol)) {
                                lowerStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    if (lowerStreak + upperStreak == n) {
                        return true;
                    }
                }
                case 3 -> {
                    for (int j = 1; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row - j][col + j].get_value(), symbol)) {
                                upperStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    for (int j = 0; j < n; j++) {
                        try {
                            if (Objects.equals(cellList[row + j][col - j].get_value(), symbol)) {
                                lowerStreak += 1;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            break;
                        }
                    }
                    if (lowerStreak + upperStreak == n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
