import java.util.Scanner;

public class TTT extends Game{

    private int streak;
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
            this.main_menu();
        }
        else {
            MainGame.startup_menu();
        }
    }

    @Override
    public void new_game(){
        Team currentTeam = this.teams[0];
        String symbol = "X";
        int s = 0;
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
                if (this.gameBoard.check_streak(streak, s)){
                    System.out.println(currentTeam.get_curr_player_name() + " has won! GG!");
                    currentTeam.add_score();
                    break;
                }
            }
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
        for (Team team : teams) {
            team.change_order();
        }
        this.main_menu();
    }

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
            System.out.println("Enter the length of the board (Default: 3, Maximum: 15. The winning rule would change as the board length increases, maximum: 5 in a row ) :");
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
                System.out.println("The input number exceeds the maximum capacity. Please re-enter the number:");
            }

        }
        else if(s == 2){
            System.out.println("Enter the number of players in each team (Default:1, Maximum: 20. Players in each team get to play by order, e.g. first game player1 vs play2, second game player3 vs player4, etc):");
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
        else
        {
            this.main_menu();
        }
    }

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
