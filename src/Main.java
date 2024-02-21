import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MovieCollection movieCollection = new MovieCollection(); // Opretter et nyt objekt af typen MovieCollection
        Controller controller = new Controller(movieCollection); // Opretter en ny controller og sender movieCollection som parameter
        System.out.println("Film-Database:");

        boolean running = true;
        while (running) {

            System.out.println("Menu");
            System.out.println("1. Ny film i database");
            System.out.println("2. Afslut programmet");
            System.out.println("Skriv 1 eller 2: ");

            int valg = input.nextInt(); // Læser brugerens input for valg

            if(valg == 1) {
                System.out.println("Indtast navn på film");
                String title = input.next(); // Læser indtastet titel

                System.out.println("Indtast navn på instruktør");
                String director = input.next(); // Læser indtastet instruktør

                System.out.println("Indtast udgivelsesår");
                int year = input.nextInt(); // Læser indtastet udgivelsesår

                System.out.println("Filmlængde i min");
                int movieMinuttes = input.nextInt(); // Læser indtastet filmlængde

                System.out.println("Genre");
                String genre = input.next(); // Læser indtastet genre

                Movie movie = new Movie(title, director, year, movieMinuttes, genre); // Opretter en ny film med de indtastede oplysninger

                controller.addMovie(movie); // Tilføjer den nye film til filmsamlingen via controlleren
                System.out.println("Film tilføjet: " + title + " Instruktør: " + director +
                        " År: " + year + " Varighed: " + movieMinuttes + " minutter Genre: " + genre);

            }
            else if (valg == 2){
                System.out.println("Nok filmdatabasering for i dag aflut");
                running = false; // Stopper while-løkken og dermed programmet
            }
            else {
                System.out.println("Du SKAL skrive tal 1 eller 2. Prøv igen :)"); // Fejlbesked hvis brugeren ikke indtaster 1 eller 2
            }
        }

    }
}