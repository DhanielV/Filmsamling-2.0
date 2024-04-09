package domainmodel;

import java.util.Comparator;

public class MovieTitleComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2){
        return movie1.getTitle().compareTo(movie2.getTitle());
    }
}
