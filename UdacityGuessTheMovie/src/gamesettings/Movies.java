package gamesettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movies {
    private final List<String> movies = new ArrayList<>();
    private final int randomNumber = (int) (Math.random() * 25);

    public Movies() {
        try {
            File file = new File("movielist.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                movies.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist!");
        }
    }

    public String getRandomMovie() {
        return movies.get(randomNumber);
    }
}