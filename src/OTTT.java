import java.util.*;

public class OTTT {

    private static String[] layout;
    private static String winner;
    private static int xscore;
    private static int oscore;

    public static void init_layout(){
        layout = new String[9];
        for (int i = 0; i < 9; i++){
            layout[i] = String.valueOf(i+1);
        }
        winner = null;
    }

    public static void print_board(){
        System.out.println("+---+---+---+");
        for (int i = 0; i < 9; i++){
            System.out.println("| "+layout[i]+" | "+layout[i+1]+" | "+layout[i+2]+" |");
            System.out.println("+---+---+---+");
            i = i+2;
        }
    }

    public static void print_score(){
        System.out.println("+---+---+---+");
        System.out.println("|Score Board|");
        System.out.println("+---+---+---+");
        System.out.println("X wins "+xscore+" times!");
        System.out.println("O wins "+oscore+" times!");
        System.out.println("Game over, thanks for playing!");

    }

    public static void check_status(){
        String streak = "";
        for (int i = 0; i < 8; i++){
            switch (i) {
                case 0 -> streak = layout[0] + layout[1] + layout[2];
                case 1 -> streak = layout[3] + layout[4] + layout[5];
                case 2 -> streak = layout[6] + layout[7] + layout[8];
                case 3 -> streak = layout[0] + layout[3] + layout[6];
                case 4 -> streak = layout[1] + layout[4] + layout[7];
                case 5 -> streak = layout[2] + layout[5] + layout[8];
                case 6 -> streak = layout[0] + layout[4] + layout[8];
                case 7 -> streak = layout[2] + layout[4] + layout[6];
            }
            if (streak.equals("XXX")){
                winner = "X";
                xscore += 1;
            }
            else if (streak.equals("OOO")){
                winner = "O";
                oscore += 1;
            }
        }
    }

    public static void main(String[] args){
        Scanner slot = new Scanner(System.in);
        boolean game = true;

        xscore = 0;
        oscore = 0;

        System.out.println("Welcome to tic tac toe!");

        while (game == true){
            String symbol = "X";
            int s = 0;
            init_layout();
            print_board();
            System.out.println("Player "+symbol+" please choose your slot (by entering the number displayed on your slot): ");

            int loop = 0;
            while(winner == null){
                if (slot.hasNextLine()){
                    try {
                        String str = slot.nextLine();
                        s = Integer.valueOf(str).intValue();
                    } catch (NumberFormatException e) {
                        System.out.println("The input is not a number. Please re-enter your slot number:");
                        continue;
                    }
                    if (s < 1 || s > 9){
                        System.out.println("The input number does not represent any slot. Please re-enter your slot number:");
                        continue;
                    }
                }
                if (layout[s-1].equals(String.valueOf(s))){
                    layout[s-1] = symbol;
                }
                else{
                    System.out.println("The input number represents a occupied slot. Please re-enter your slot number:");
                    continue;
                }
                loop += 1;
                print_board();
                System.out.println(loop);
                if (loop > 4){
                    check_status();
                    if (winner == "X"){
                        System.out.println("Player X has won! GG!");
                        break;
                    }
                    else if (winner == "O"){
                        System.out.println("Player O has won! GG!");
                        break;
                    }
                }
                if (loop == 9){
                    System.out.println("It's a draw! Everyone wins!");
                    winner = "draw";
                    break;
                }
                if (loop%2 == 0) {
                    symbol = "X";
                }
                else if (loop%2 == 1) {
                    symbol = "O";
                }
                System.out.println("Player " + symbol + " please choose your slot:");
            }

            System.out.println("Move on to the next game? (enter y as yes or n as no): ");
            while (true) {
                if (slot.hasNextLine()) {
                    String ans = slot.nextLine();
                    if (ans.equals("y")) {
                        break;
                    }
                    else if (ans.equals("n")) {
                        game = false;
                        break;
                    }
                    else {
                        System.out.println("Invalid input, please re-enter (enter y as yes or n as no): ");
                    }
                }
            }
        }
        print_score();
    }

}



