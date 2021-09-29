public class TTT extends Game{

    int streak;

    TTT(){
        super("Tic Tac Toe", 0);
        streak = 0;
    }

    TTT(int n){
        super("Tic Tac Toe", n);
        if (n <= 5){
            streak = n;
        }
        else{
            streak = 5;
        }
    }

    @Override
    public void check_winner() {

    }
}
