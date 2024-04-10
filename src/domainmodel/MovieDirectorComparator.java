package domainmodel;

import java.util.Comparator;

public class MovieDirectorComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2){
        return movie1.getDirector().compareTo(movie2.getDirector());
    }
}
