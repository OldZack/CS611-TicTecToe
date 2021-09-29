public abstract class Game {

    protected String gameName;
    protected Board gameBoard;

    Game(){
        gameName = null;
        gameBoard = new Board();
    }

    Game(String name, int n){
        gameName = name;
        gameBoard = new Board(n);
    }

    abstract public void check_winner();

}

