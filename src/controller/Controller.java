package controller;

import domainmodel.Movie;
import domainmodel.MovieCollection;
import domainsource.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {
    public final FileHandler fileHandler;
    private final MovieCollection movieCollection;
    private boolean changesMade = false;


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


    public String displayMovieList() {
        return movieCollection.displayMovieList();
    }

    public String displayMovieListSortTitleAlphabetically() {
        return movieCollection.displayMovieListSortTitleAlphabetically();
    }

    public String displayMovieListSortDirectorAlphabetically() {
        return movieCollection.displayMovieListSortDirectorAlphabetically();
    }

    public String displayMovieListSortMovieMinutesAscending() {
        return movieCollection.displayMovieListSortMovieMinutesAscending();
    }

    public String displayMovieListSortYearAscending() {
        return movieCollection.displayMovieListSortYearAscending();
    }

    public String displayMovieListSortGenreAlphabetically() {
        return movieCollection.displayMovieListSortGenreAlphabetically();
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
}