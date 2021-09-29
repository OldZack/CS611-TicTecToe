import java.util.Objects;
import java.util.Scanner;

public class OAC extends Game {
    /*
    This class defines a game called Order and Chaos, inherited from class Game.
    Function of each method is explained above it.
    */
    private final Scanner slot = new Scanner(System.in);

    OAC(){
        super("Order and Chaos", 6, 2);
    }

    /*
    Print the main menu which can generate a new game, change team size, print scoreboard or quit the game.
    */
    @Override
    public void main_menu() {
        System.out.println(".╔══════════════════╗.");
        System.out.println(".‖. Order and Chaos .‖.");
        System.out.println(".‖.    Main Menu    .‖.");
        System.out.println(".╚══════════════════╝.");

        System.out.println("Welcome to the game of order and chaos! ");
        System.out.println("1. Start Game");
        System.out.println("2. Set Team Size (will reset the scoreboard)");
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
            this.set_team_size();
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
    public void new_game() {
        Team orderTeam, chaosTeam, currentTeam;
        // Randomly choose one player to be the order's side while the other represents chaos.
        if (Math.random() <= 0.5){
            orderTeam = this.teams[0];
            chaosTeam = this.teams[1];
        }
        else{
            orderTeam = this.teams[1];
            chaosTeam = this.teams[0];
        }
        currentTeam = orderTeam;
        int s = 0;              // Position of the cell that user picks.
        String symbol = " ";    // The symbol that user puts on the cell.
        this.gameBoard.print_board();
        System.out.println(orderTeam.get_curr_player_name() +"(Order) please enter your slot number and symbol (separated by space): ");
        int loop = 0;
        while(true){
            if (slot.hasNextLine()){
                String [] str = slot.nextLine().split("\\s+");
                try{
                    s = Integer.parseInt(str[0]);
                    symbol = str[1];
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("The input does not contain both slot and symbol. Please re-enter:");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("The input does not represent any slot. Please re-enter:");
                    continue;
                }
                if (s < 1 || s > 36){
                    System.out.println("The input does not represent any slot. Please re-enter:");
                    continue;
                }
                if (!Objects.equals(symbol, "X") && !Objects.equals(symbol, "O")){
                    System.out.println("The input symbol is not X or O. Please re-enter:");
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
            if (loop >= 4){
                /*
                When there's enough loops to have a winner, check every time when a new move is made.
                if the move generates a 5 in a row but not a 6 in a row, the order side wins.
                */
                if (this.gameBoard.check_streak(5, s) && !this.gameBoard.check_streak(6, s)){
                    System.out.println("The Order side has won! GG!");
                    orderTeam.add_score();
                    break;
                }
            }
            // If the board is packed then the chaos side wins.
            if (loop == 36) {
                System.out.println("The Chaos side has won! GG!");
                chaosTeam.add_score();
                break;
            }
            if (loop%2 == 0) {
                currentTeam = orderTeam;
            }
            else if (loop%2 == 1) {
                currentTeam = chaosTeam;
            }
            System.out.println(currentTeam.get_curr_player_name() + " please enter your slot and symbol:");
        }
        this.gameBoard.clean_board();
        // Let the next player in each team to play the next game.
        for (Team team : teams) {
            team.change_order();
        }
        this.main_menu();
    }

    /*
    Changes the number of players in each team.
    */
    public void set_team_size(){
        System.out.println("Enter the number of players in each team (Default:1, Maximum: 20. Players in each team get to play by order, e.g. first game wil be player1 vs player2, second game wil be player3 vs player4, etc):");
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
                this.main_menu();
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
