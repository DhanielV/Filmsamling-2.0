package domainmodel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MovieCollection {
    private final ArrayList<Movie> movies = new ArrayList<>(); // Array til at gemme film

    private final Comparator<Movie> movieTitleComparator = new MovieTitleComparator();
    private final Comparator<Movie> movieDirectorComparator = new MovieDirectorComparator();
    private final Comparator<Movie> moveMinutesComparator = new MovieMovieMinutesComparator();
    private final Comparator<Movie> movieYearComparator = new MovieYearComparator();
    private final Comparator<Movie> movieGenreComparator = new MovieGenreComparator();



    public String displayMovieList() {
        return generateMovieListString();
    }

    private String generateMovieListString() {
        StringBuilder result = new StringBuilder();
        for (Movie movie : movies) {
            result.append("Title: ").append(movie.getTitle()).append("\n").append("Director: ").append(movie.getDirector()).append("\n").append("Year: ").append(movie.getYear()).append("\n").append("Duration: ").append(movie.getMovieMinutes()).append(" minuttes\n").append("Genre: ").append(movie.getGenre()).append("\n\n");

        }
        return result.toString();
    }

    public String displayMovieListSortTitleAlphabetically() {
        Collections.sort(movies, movieTitleComparator);
        return generateMovieListString();
    }

    public String displayMovieListSortDirectorAlphabetically() {
        Collections.sort(movies, movieDirectorComparator);
        return generateMovieListString();
    }

    public String displayMovieListSortMovieMinutesAscending(){
        Collections.sort(movies, moveMinutesComparator);
        return generateMovieListString();
    }

    public String displayMovieListSortYearAscending(){
        Collections.sort(movies, movieYearComparator);
        return generateMovieListString();
    }

    public String displayMovieListSortGenreAlphabetically(){
        Collections.sort(movies, movieGenreComparator);
        return generateMovieListString();
    }


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

