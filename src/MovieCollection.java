public class MovieCollection {
    private Movie[] movies = new Movie[5]; // Opretter et array til at gemme film
    int currentIndex = 0; // Variabel til at holde styr på den aktuelle position i arrayet
    public Movie[] getMovies(){
        return movies; // Returnerer arrayet med film
    }
    public void addMovie(Movie movie){
        movies[currentIndex] = movie; // Tilføjer en ny film til arrayet
        currentIndex++; // Opdaterer den aktuelle position
    }
}