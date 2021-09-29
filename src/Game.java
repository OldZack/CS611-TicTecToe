public abstract class Game {
    /*
    This class is an abstract class that defines the basic elements for a board game,
    including game's name, game board and player teams.
    */
    protected String gameName;
    protected Board gameBoard;
    protected Team[] teams;

    Game(){
        gameName = null;
        gameBoard = new Board();
        teams = new Team[1];
        teams[0] = new Team(1, 1);
    }

    Game(String name, int boardSize, int teamSize){
        gameName = name;
        gameBoard = new Board(boardSize);
        teams = new Team[teamSize];
        for (int i = 0; i < teamSize; i++){
            teams[i] = new Team(1, i+1);
        }
    }

    abstract public void main_menu();
    abstract public void new_game();

}

