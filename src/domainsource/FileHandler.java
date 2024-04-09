package domainsource;

import domainmodel.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final File file = new File("movies.txt");


    public ArrayList<Movie> loadMovies() throws FileNotFoundException {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] movieData = line.split(",");
                String title = movieData[0];
                String director = movieData[1];
                int year = Integer.parseInt(movieData[2]);
                int movieMinutes = Integer.parseInt(movieData[3]);
                String genre = movieData[4];

                Movie movie = new Movie(title, director, year, movieMinutes, genre);
                movies.add(movie);

            }
        }
        return movies;




    }
    public void saveMovies(ArrayList<Movie> movies) {
        try (PrintStream output = new PrintStream(file)) {
            for (Movie movie : movies) {
                output.println(movie.getTitle() + "," + movie.getDirector() + "," + movie.getYear() + "," + movie.getMovieMinutes() + "," + movie.getGenre());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error saving movies to file: " + e.getMessage());
        }
    }
}
