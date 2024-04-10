package domainmodel;

import java.util.Comparator;

public class MovieGenreComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2){
        return movie1.getGenre().compareTo(movie2.getGenre());
    }
}
