public class Controller {
    private MovieCollection movieCollection;
    public Controller(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
    }
    public void addMovie(Movie movie){
        movieCollection.addMovie(movie); // Kalder addMovie metoden i movieCollection for at tilføje en ny film
    }
}