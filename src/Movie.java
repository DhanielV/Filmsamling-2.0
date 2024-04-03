public class Movie {
    private String title; // Attribut for titel
    private String director; // Attribut for instruktør
    private int year; // Attribut for udgivelsesår
    private int movieMinutes; // Attribut for filmlængde i minutter
    private String genre; // Attribut for genre

    // Konstruktør til at initialisere en film med givne oplysninger
    public Movie(String title, String director, int year, int movieMinutes, String genre) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.movieMinutes = movieMinutes;
        this.genre = genre;
    }

    // Getter-metoder for at få adgang til filmattributter
    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public int getMovieMinutes() {
        return movieMinutes;
    }

    public String getGenre() {
        return genre;
    }
}