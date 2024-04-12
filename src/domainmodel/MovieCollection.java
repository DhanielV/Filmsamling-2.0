package domainmodel;


import java.util.ArrayList;


public class MovieCollection {
    private final ArrayList<Movie> movies = new ArrayList<>(); // Array til at gemme film




    public void addMovie(Movie movie) throws RuntimeException {
        movies.add(movie);

    }


    public void removeMovie(Movie movieToRemove) {
        movies.remove(movieToRemove);
    }

    public Movie searchTitle(String title) { //Søg på filmtitel
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    public ArrayList<Movie> searchDirector(String director) {
        ArrayList<Movie> foundMovies = new ArrayList<>();//Søg på filmtitel
        for (Movie movie : movies) {
            if (movie.getDirector().equalsIgnoreCase(director)) {
                foundMovies.add(movie);
            }
        }
        return foundMovies;
    }


    public ArrayList<Movie> searchGenre(String genre) {
        ArrayList<Movie> foundMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                foundMovies.add(movie);
            }
        }
        return foundMovies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}

