public abstract class Game {

    String gameName;
    Board gameBoard;

    Game(){
        gameName = null;
        gameBoard = new Board();
    }

    Game(String name, int n){
        gameName = name;
        gameBoard = new Board(n);
    }

    abstract public void print_board();
    abstract public void check_winner();

}

