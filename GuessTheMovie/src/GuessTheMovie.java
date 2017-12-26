import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class GuessTheMovie {
    private String randomMovie;
    private String guessMovie;
    private Scanner scanner;
    private ArrayList<Character> guessedLetters = new ArrayList<>();
    private ArrayList<Character> usedLetters = new ArrayList<>();
    private int lives = 10;

    GuessTheMovie(){
        try{
            ArrayList<String> movieList = new ArrayList<>();
            File file = new File("movieList.txt");
            scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String movieTitle = scanner.nextLine().trim();
                movieList.add(movieTitle);
            }

            int randomNum = (int) (Math.random() * (movieList.size()));
            randomMovie = movieList.get(randomNum).toLowerCase();
            guessMovie = randomMovie.replaceAll("[a-zA-Z]", "_").trim();

            intro();

        } catch (FileNotFoundException error){
            System.out.println("Error! File was not found.");
        }
        CheckGuess();
    }

    private void CheckGuess() {

            while(!HasWon(guessMovie) && lives > 0){
                System.out.println("Enter your guess: ");

                scanner = new Scanner(System.in);
                char userGuess = scanner.next().toLowerCase().charAt(0);

                if(Character.isDigit(userGuess)){
                    System.out.println("There are no digits!");
                }
                if(containGuess(userGuess)){
                    StringBuilder answer = new StringBuilder(guessMovie);

                    if(guessedLetters.contains(userGuess)){
                        System.out.println("You've already guessed that!");
                    } else {
                        guessedLetters.add(userGuess);

                    }
                    for(int i = 0; i < randomMovie.length(); i++){
                        if(randomMovie.charAt(i) == userGuess){
                            answer.setCharAt(i, userGuess);
                            guessMovie = answer.toString();
                        }
                    }
                    if (HasWon(guessMovie)){
                        System.out.println("You have won the game, congratulations!" + " The film was " + randomMovie);
                    } else if (lives == 0){
                        System.out.println("You lost, the movie was " + randomMovie);
                    } else {
                        System.out.println(guessMovie);
                    }
                }
                else {
                    System.out.println("Sorry, there is no " + userGuess);
                    if(usedLetters.contains(userGuess)){
                        System.out.println("Stop guessing that!");
                    } else {
                        usedLetters.add(userGuess);
                        lives--;
                        if (lives == 0){
                            System.out.println();
                            System.out.println("You lost, the movie was " + randomMovie);
                        }
                    }
                }
            }
        }

    private boolean containGuess(char userGuess){
        return randomMovie.indexOf(userGuess) > -1;
    }

    private boolean HasWon(String guessString){
        return !guessString.contains("_");
    }

    private void intro(){
        System.out.println("Welcome to \"Guess The Movie\"!");
        System.out.println("The film is randomly selected from a list of the 2017 box office");
        System.out.println("You have 10 lives to correctly guess the encrypted film");
        System.out.println(guessMovie);
    }
}


