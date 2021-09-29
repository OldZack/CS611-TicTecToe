import java.util.Scanner;

public class TTT extends Game{
    /*
    This class defines a game called Tic Tac Toe, inherited from class Game.
    Function of each method is explained above it.
    */
    private int streak;         // Required number of symbols that could form a winning streak.
    private final Scanner slot = new Scanner(System.in);

    TTT(){
        super("Tic Tac Toe", 3, 2);
        streak = 3;
    }

    TTT(int n){
        super("Tic Tac Toe", n, 2);
        if (n <= 5){
            streak = n;
        }
        else{
            streak = 5;
        }
    }

    /*
    Print the main menu which can generate a new game, open setting menu, print scoreboard or quit the game.
    */
    @Override
    public void main_menu() {

        System.out.println(".╔══════════════╗.");
        System.out.println(".‖. Tic Tac Toe .‖.");
        System.out.println(".‖.  Main Menu  .‖.");
        System.out.println(".╚══════════════╝.");

        System.out.println("Welcome to the game of tic tac toe! ");
        System.out.println("1. Start Game");
        System.out.println("2. Settings");
        System.out.println("3. Scoreboard");
        System.out.println("4. Quit Game");

        int s;
        while (true){
            try {
                String str = slot.nextLine();
                s = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter the choice number:");
                continue;
            }
            if (s >= 1 && s <=4){
                break;
            }
            System.out.println("The input number exceeds the number of choices. Please re-enter the number:");
        }

        if (s == 1){
            this.new_game();
        }
        else if (s == 2){
            this.setting_menu();
        }
        else if (s == 3){
            this.print_score();
        }
        else {
            MainGame.startup_menu();
        }
    }

    /*
    Generate a new game, users would interact with the game until its finished.
    */
    @Override
    public void new_game(){
        // Let the first team to go first.
        Team currentTeam = this.teams[0];
        // Let symbol X to go first.
        String symbol = "X";
        int s = 0;      // Position of the cell that user picks.
        int size = this.gameBoard.get_size();
        this.gameBoard.print_board();
        System.out.println(currentTeam.get_curr_player_name() +" please choose your slot (by entering the number displayed on your slot): ");

        int loop = 0;
        while(true){
            if (slot.hasNextLine()){
                try {
                    String str = slot.nextLine();
                    s = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.out.println("The input is not a number. Please re-enter your slot number:");
                    continue;
                }
                if (s < 1 || s > size){
                    System.out.println("The input number does not represent any slot. Please re-enter your slot number:");
                    continue;
                }
            }
            // If the cell is not been modified, change its symbol to the user-defined one.
            if (this.gameBoard.get_value(s).equals(String.valueOf(s))){
                this.gameBoard.set_value(s, symbol);
            }
            else{
                System.out.println("The input number represents a occupied slot. Please re-enter your slot number:");
                continue;
            }
            loop += 1;
            this.gameBoard.print_board();
            if (loop >= 2*streak-1){
                /*
                When there's enough loops to have a winner, check every time when a new move is made.
                if the move generates winning streak, the current player wins.
                */
                if (this.gameBoard.check_streak(streak, s)){
                    System.out.println(currentTeam.get_curr_player_name() + " has won! GG!");
                    currentTeam.add_score();
                    break;
                }
            }
            // If the board is packed then there is a tie.
            if (loop == size){
                System.out.println("It's a draw! Everyone wins!");
                break;
            }
            if (loop%2 == 0) {
                currentTeam = this.teams[0];
                symbol = "X";
            }
            else if (loop%2 == 1) {
                currentTeam = this.teams[1];
                symbol = "O";
            }
            System.out.println(currentTeam.get_curr_player_name() + " please choose your slot:");
        }
        this.gameBoard.clean_board();
        // Let the next player in each team to play the next game.
        for (Team team : teams) {
            team.change_order();
        }
        this.main_menu();
    }

    /*
    Generate the setting menu which can let user change the size of the board
    as well as the number of players in each team.
    */
    public void setting_menu(){
        System.out.println(".╔══════════════╗.");
        System.out.println(".‖. Tic Tac Toe .‖.");
        System.out.println(".‖. Setup  Menu .‖.");
        System.out.println(".╚══════════════╝.");

        System.out.println("1. Set board size");
        System.out.println("2. Set team size (will resect scoreboard)");
        System.out.println("3. Back to main menu");

        int s;
        while (true){
            try {
                String str = slot.nextLine();
                s = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter the choice number:");
                continue;
            }
            if (s >= 1 && s <=3){
                break;
            }
            System.out.println("The input number exceeds the number of choices. Please re-enter the number:");
        }

        if (s == 1){
            this.set_board_size();
        }
        else if(s == 2){
            this.set_team_size();
        }
        else
        {
            this.main_menu();
        }
    }

    /*
    Changes the size of the board, the winning condition also changes according to it.
    */
    public void set_board_size(){
        System.out.println("Enter the length of the board (Default: 3, Maximum: 15. The winning rule would change as the board length increases, maximum: 5 in a row ) :");
        int s;
        while (true) {
            try {
                String str = slot.nextLine();
                s = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter the length number:");
                continue;
            }
            if (s >= 3 && s <= 15) {
                this.gameBoard.change_board(s);
                if (s <= 5){
                    streak = s;
                }
                else{
                    streak = 5;
                }
                this.setting_menu();
                break;
            }
            System.out.println("The input number exceeds the capacity. Please re-enter the number:");
        }
    }

    /*
    Changes the number of players in each team.
    */
    public void set_team_size(){
        System.out.println("Enter the number of players in each team (Default:1, Maximum: 20. Players in each team get to play by order, e.g. first game will be player1 vs player2, second game wil be player3 vs player4, etc):");
        int s;
        while (true) {
            try {
                String str = slot.nextLine();
                s = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter the size number:");
                continue;
            }
            if (s >= 1 && s <= 20) {
                for (int i = 0; i < teams.length; i++){
                    this.teams[i].change_team_size(s, i+1);
                }
                this.setting_menu();
                break;
            }
            System.out.println("The input size is too large. Please re-enter the number:");
        }
    }

    /*
    Print the scoreboard of each team, user can choose to keep playing or quit the game.
    */
    public void print_score(){
        System.out.println("╔─────────────╗");
        System.out.println("│ Score Board │");
        System.out.println("╚─────────────╝");
        System.out.println("╔═══════ Team 1");
        for (int j = 0; j < this.teams[0].num_players(); j++){
            System.out.println(" "+this.teams[0].get_player_name(j)+":   "+this.teams[0].get_player_score(j));
        }
        System.out.println(" Total:      "+this.teams[0].get_score());
        System.out.println("Team 2 ═══════╗");
        for (int j = 0; j < this.teams[1].num_players(); j++){
            System.out.println(this.teams[1].get_player_name(j)+":   "+this.teams[1].get_player_score(j));
        }
        System.out.println("Total:      "+this.teams[1].get_score());

        System.out.println("Back to the game menu? ");
        System.out.println("1. Yes");
        System.out.println("2. No (Quit the game）");

        int s;
        while (true) {
            try {
                String str = slot.nextLine();
                s = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter the size number:");
                continue;
            }
            if (s == 1 || s == 2) {
                break;
            }
            System.out.println("The input size is too large. Please re-enter the number:");
        }
        if (s == 1){
            this.main_menu();
        }
        if (s == 2){
            System.out.println("Thanks for playing!");
        }
    }
}
