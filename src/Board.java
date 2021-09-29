public class Board {

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
                cellList[i][j] = new Cell(String.valueOf(i+j+1), i, j);
        }
        length = n;
    }

    public void print_board(int n) {
        for (int i = 0; i < n; i++){
            System.out.print("+---");
        }
        System.out.println("+");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("| " + cellList[i][j].get_value());
            }
            System.out.println("|");
        }

        for (int i = 0; i < n; i++){
            System.out.print("+---");
        }
        System.out.println("+");
    }

    public boolean check_streak(int n, int p){
        int row = (int)(p-1)/length;
        int col = (p-1)%length;
        String symbol = cellList[row][col].get_value();
        for (int i = 0; i < 4; i++) {
            int upperStreak = 1;
            int lowerStreak = 1;
            switch (i) {
                case 0:
                    for (int j = row+1; j < length; j++){
                        if (cellList[j][col].get_value() == symbol){
                            upperStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    for (int j = row-1; j > -1; j--){
                        if (cellList[j][col].get_value() == symbol){
                            lowerStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    if (upperStreak + lowerStreak == n){
                        return true;
                    }

                case 1:
                    for (int j = col+1; j < length; j++){
                        if (cellList[row][j].get_value() == symbol){
                            upperStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    for (int j = col-1; j > -1; j--){
                        if (cellList[row][j].get_value() == symbol){
                            lowerStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    if (upperStreak + lowerStreak == n){
                        return true;
                    }
                case 2:
                    for (int j = 1; j+Math.max(row, col) < length; j++){
                        if (cellList[row+j][col+j].get_value() == symbol){
                            upperStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    for (int j = -1; j+Math.min(row, col) > -1 ; j--){
                        if (cellList[row+j][col+j].get_value() == symbol){
                            lowerStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    if (upperStreak + lowerStreak == n){
                        return true;
                    }
                case 3:
                    for (int j = row+1; j < length; j++){
                        if (cellList[j][col].get_value() == symbol){
                            upperStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    for (int j = row-1; j > 0; j--){
                        if (cellList[j][col].get_value() == symbol){
                            lowerStreak += 1;
                        }
                        else{
                            break;
                        }
                    }
                    if (upperStreak + lowerStreak == n){
                        return true;
                    }
            }
        }
        return false;
    }


}
