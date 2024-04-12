package controller;

import domainmodel.*;
import domainsource.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Controller {
    public final FileHandler fileHandler;
    private final MovieCollection movieCollection;
    private boolean changesMade = false;

    private final Comparator<Movie> movieTitleComparator = new MovieTitleComparator();
    private final Comparator<Movie> movieDirectorComparator = new MovieDirectorComparator();
    private final Comparator<Movie> moveMinutesComparator = new MovieMovieMinutesComparator();
    private final Comparator<Movie> movieYearComparator = new MovieYearComparator();
    private final Comparator<Movie> movieGenreComparator = new MovieGenreComparator();









    private String generateMovieListString() {
        StringBuilder result = new StringBuilder();
        ArrayList<Movie> movies = movieCollection.getMovies();
        for (Movie movie : movies) {
            result.append("Title: ").append(movie.getTitle()).append("\n").append("Director: ").append(movie.getDirector()).append("\n").append("Year: ").append(movie.getYear()).append("\n").append("Duration: ").append(movie.getMovieMinutes()).append(" minuttes\n").append("Genre: ").append(movie.getGenre()).append("\n\n");

        }
        return result.toString();
    }


    public String displayMovieListSort(Comparator<Movie> comparator) {
        ArrayList<Movie> movies = movieCollection.getMovies();
        sortMovies(movies, comparator);
        return generateMovieListString();
    }

    private void sortMovies(ArrayList<Movie> movies, Comparator<Movie> comparator) {
        Collections.sort(movies, comparator);
    }
    public Comparator<Movie> getMovieTitleComparator(){
        return movieTitleComparator;
    }

    public Comparator<Movie> getMovieDirectorComparator() {
        return movieDirectorComparator;
    }

    public Comparator<Movie> getMoveMinutesComparator() {
        return moveMinutesComparator;
    }

    public Comparator<Movie> getMovieYearComparator() {
        return movieYearComparator;
    }

    public Comparator<Movie> getMovieGenreComparator() {
        return movieGenreComparator;
    }



    public Controller() { // Constructor
        this.fileHandler = new FileHandler();
        this.movieCollection = new MovieCollection();
    }

    public void addMovie(Movie movie) {
        movieCollection.addMovie(movie); // Kalder addMovie metoden i movieCollection for at tilføje en ny film
        changesMade = true;


    }
    public void movieEdited(){
        changesMade = true;
    }

    public void saveMovies() {
        if (changesMade) {
            ArrayList<Movie> moviesToSave = movieCollection.getMovies();
            fileHandler.saveMovies(moviesToSave);
        }

    }





    public void removeMovie(Movie movie) {
        movieCollection.removeMovie(movie);
        changesMade = true;
    }

    public Movie searchTitle(String title) {
        return movieCollection.searchTitle(title);
    }


    public ArrayList<Movie> searchDirector(String director) {
        return movieCollection.searchDirector(director);
    }

    public ArrayList<Movie> searchGenre(String genre) {
        return movieCollection.searchGenre(genre);
    }

    public void loadMovies() {
        try {
            ArrayList<Movie> loadedMovies = fileHandler.loadMovies();
            for (Movie movie : loadedMovies) {
                movieCollection.addMovie(movie);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e.getMessage());
        }
    }


    public String displayUnMovieListSort() {
        return generateMovieListString();
    }
}