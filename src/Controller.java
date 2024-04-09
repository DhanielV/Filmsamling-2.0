import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {
    public final FileHandler fileHandler;
    private MovieCollection movieCollection;


    public Controller() { // Constructor
        this.fileHandler = new FileHandler();
        this.movieCollection = new MovieCollection();
    }

    public void addMovie(Movie movie) {
        movieCollection.addMovie(movie); // Kalder addMovie metoden i movieCollection for at tilføje en ny film


    }

    public void saveMoviesOnExit() {
        try {
            movieCollection.saveMovies();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public String displayMovieList() {
        return movieCollection.displayMovieList();
    }

    public void removeMovie(Movie movie) {
        movieCollection.removeMovie(movie);
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
    public void loadMovies(){
        try {
            ArrayList<Movie> loadedMovies = fileHandler.loadMovies();
            for (Movie movie : loadedMovies) {
                movieCollection.addMovie(movie);
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException("File not found: " + e.getMessage());
        }
    }
}