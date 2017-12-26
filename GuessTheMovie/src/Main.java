import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new GuessTheMovie();
        System.out.println("--------------------------");
        System.out.println("Thanks for playing :)");
        System.out.println("Press enter to play again");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String continuePlay = null;

        try {
            continuePlay = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert continuePlay != null;
        if (continuePlay.length() == 0) {
            new GuessTheMovie();
        }
    }
}
