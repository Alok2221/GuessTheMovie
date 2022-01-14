package gamesettings;

import java.awt.*;
import java.util.Scanner;

public class Game {

    private final String movieToGuess;
    private int pointsLost;
    private String wrongLetters;
    private String rightLetters;
    private boolean gameWon;

    public Game() {
        Movies movieList = new Movies();
        movieToGuess = movieList.getRandomMovie().trim();
        pointsLost = 0;
        rightLetters = "";
        wrongLetters = "";
        gameWon = false;
    }

    private String getMovieTitle() {
        return movieToGuess;
    }

    private String hiddenTitle() {
        if (rightLetters.equals("")) {
            return movieToGuess.replaceAll("[a-zA-Z]", "_");
        } else {
            return movieToGuess.replaceAll("[a-zA-Z&&[^" + rightLetters + "]]", "_");
        }
    }

    private String getWrongLetters() {
        return wrongLetters;
    }

    public void start(Game game) {
        System.out.println("Welcome to Guess the Movie!!!");
        System.out.println("The rules are simple, the computer randomly picks a movie title, and shows you how " +
                "many " + "letters it's made up of.");
        System.out.println("Your task is to figure out what's the movie title by guessing one letter at a time.");
        System.out.println("If a letter is indeed in the title it will be revealed if not, you lose a point.");
        System.out.println("If you lose 10 points, game over!!!");
        System.out.println("Let's start!");
        System.out.println("The movie title has " + game.getMovieTitle().length() + " characters (including spaces and punctuation).");

        while (!game.gameEnded()) {
            System.out.println("You are guessing:" + game.hiddenTitle());
            System.out.println("You have guessed (" + game.getWrongLetters().length() / 2 + ") wrong letters:"
                    + game.getWrongLetters());
            game.guessLetter();
        }
        if (game.WonGame()) {
            System.out.println("You win!");
            System.out.println("You have guessed " + game.getMovieTitle() + " correctly.");
        } else {
            System.out.println("You have guessed (" + game.getWrongLetters().length() / 2 + ") wrong letters:" +
                    game.getWrongLetters());
            System.out.println("You lost!");
            System.out.println("The movie title was " + game.getMovieTitle());
            System.out.println("Better luck next time.");
        }
    }

    private boolean WonGame() {
        return gameWon;
    }

    private boolean gameEnded() {
        if (pointsLost >= 10) {
            return true;
        }

        if (!hiddenTitle().contains("_")) {
            gameWon = true;
            return true;
        }
        return false;
    }

    private String inputLetter() {

        System.out.println("Guess a letter:");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine().toLowerCase();
        if (!letter.matches("[a-z]")) {
            System.out.println("That is not a letter.");
            return inputLetter();
        } else if (wrongLetters.contains(letter) || rightLetters.contains(letter)) {
            System.out.println("You already guessed that letter.");
            return inputLetter();
        } else {
            return letter;
        }
    }

    private void guessLetter() {

        String guessedLetter = inputLetter();
        if (movieToGuess.toLowerCase().contains(guessedLetter)) {
            rightLetters += guessedLetter + guessedLetter.toUpperCase();
        } else {
            pointsLost++;
            wrongLetters += " " + guessedLetter;
        }
    }
}