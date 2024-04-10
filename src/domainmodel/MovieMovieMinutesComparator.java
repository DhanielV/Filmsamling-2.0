package domainmodel;

import java.util.Comparator;

public class MovieMovieMinutesComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2){
        return movie1.getMovieMinutes() - (movie2.getMovieMinutes());
    }
}
