public class Board {

    private Cell [][] cellList;

    public Board(){
        cellList = new Cell[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++)
            cellList[i][j] = new Cell();
        }
    }

    public Board( int n ){
        cellList = new Cell[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++)
                cellList[i][j] = new Cell(String.valueOf(i+j+1), i, j);
        }
    }


}
