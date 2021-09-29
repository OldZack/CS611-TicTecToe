import java.util.Scanner;

public class MainGame {
    /*
    This is the main class, which let users pick the game they want to play.
    */
    public static void startup_menu(){
        Scanner slot = new Scanner(System.in);

        System.out.println("┏☆━━━━━━━━━━━━━━━━━━━━━━━━━━━━☆┓");
        System.out.println("☆  Welcome to Zack's Game Set  ☆");
        System.out.println("┗☆━━━━━━━━━━━━━━━━━━━━━━━━━━━━☆┛");
        System.out.println("Please choose game you would like to play by entering the game number:");
        System.out.println("1. TicTacToe");
        System.out.println("2. Order and Chaos");
        System.out.println("More games on the way...");
        System.out.println("0. Quit Game");

        int s;
        while (true){
            try {
                String str = slot.nextLine();
                s = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("The input is not a number. Please re-enter the game number:");
                continue;
            }
            if (s == 1 || s == 2 || s == 0){
                break;
            }
            System.out.println("The input number exceeds the number of games we offer. Please re-enter your game number:");
        }

        if (s == 0){
            System.out.println("Thanks for playing!");
            return;
        }
        if (s == 1){
            Game ttt = new TTT();
            ttt.main_menu();
        }
        if (s == 2){
            Game oac = new OAC();
            oac.main_menu();
        }

    }

    public static void main(String[] args){
        MainGame.startup_menu();
    }
}
