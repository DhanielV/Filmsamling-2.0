import java.util.ArrayList;

public class Controller {
    private MovieCollection movieCollection;

    public Controller() { // Constructor
        this.movieCollection = new MovieCollection();
    }

    public void addMovie(Movie movie) {
        movieCollection.addMovie(movie); // Kalder addMovie metoden i movieCollection for at tilføje en ny film
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
}