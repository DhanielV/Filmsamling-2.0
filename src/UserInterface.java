import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    Scanner input = new Scanner(System.in);

    Controller controller = new Controller(); // Opretter en ny controller og sender movieCollection som parameter
    private boolean running = true;

    public void startProgram() {
        System.out.println("Film-Database:");

        while (running) {
            displayMenu();
            int valg = input.nextInt();
            menuValg(valg);
        }
    }

    private void displayMenu() {


        System.out.println("Menu");
        System.out.println("1. Ny film i database");
        System.out.println("2. Se filmliste");
        System.out.println("3. søg på filmtitel, instruktør eller genre");
        System.out.println("4. Rediger i en filmtitel");
        System.out.println("5. Fjern film");
        System.out.println("6. Afslut programmet");
        System.out.println("Skriv 1, 2, 3, 4, 5 eller 6: ");
    }

    private void menuValg(int valg) {
        switch (valg) {

            case 1:
                //Nye film
                System.out.println("Indtast navn på film");
                String title = input.next();

                System.out.println("Indtast navn på instruktør");
                String director = input.next();

                int year = 0;
                boolean validYear = false;
                while (!validYear) {
                    System.out.println("Indtast udgivelsesår");
                    if (input.hasNextInt()) {
                        year = input.nextInt();
                        validYear = true;
                    } else {
                        System.out.println("You must type a number try again");
                        input.next();
                    }
                }


                int movieMinuttes = 0;
                boolean validLength = false;
                while (!validLength) {
                    System.out.println("Filmlængde i min");
                    if (input.hasNextInt()) {
                        movieMinuttes = input.nextInt();
                        validLength = true;
                    } else {
                        System.out.println("You must type a number try again");
                        input.next();

                    }
                }


                System.out.println("Genre");
                String genre = input.next();

                Movie movie = new Movie(title, director, year, movieMinuttes, genre); // Opretter en ny film med de indtastede oplysninger

                controller.addMovie(movie); // Tilføjer den nye film til DB via controlleren
                System.out.println("Film tilføjet: " + title + "\nInstruktør: " + director +
                        "\nÅr: " + year + "\nVarighed: " + movieMinuttes + " minutter\nGenre: " + genre);
                break;

            case 2:
                System.out.println("Liste over film i databasen:\n");
                String displayMovieList = controller.displayMovieList();
                System.out.println(displayMovieList);

                break;

            case 3:
                System.out.println("Tast 1-3 for at vælge at søge på: \n 1. Titel \n 2. Instruktør \n 3. Genre ");
                int searchChoice = input.nextInt();
                switch (searchChoice) {
                    case 1:
                        System.out.println("Indtast titel på film du søger");
                        String searchTitle = input.next();
                        Movie foundTitleMovie = controller.searchTitle(searchTitle);
                        if (foundTitleMovie != null) {
                            System.out.println("Film fundet: " + foundTitleMovie.getTitle());

                        } else {
                            System.out.println("Ingen matchende film");
                        }
                        break;
                    case 2:
                        System.out.println("Indtast instruktør");

                        String searchDirector = input.next();
                        ArrayList<Movie> foundDirectorMovies = controller.searchDirector(searchDirector);
                        if (foundDirectorMovies != null && !foundDirectorMovies.isEmpty()) {
                            System.out.println("Film fundet med intruktør: " + searchDirector + ":");
                            for (Movie foundDirectorMovie : foundDirectorMovies) {
                                System.out.println(foundDirectorMovie.getTitle());
                            }
                        } else {
                            System.out.println("Ingen matchende film");
                        }
                        break;


                    case 3:
                        System.out.println("Indtast genre");
                        String searchGenre = input.next();
                        ArrayList<Movie> foundGenreMovies = controller.searchGenre(searchGenre);
                        if (foundGenreMovies != null && !foundGenreMovies.isEmpty()) {
                            System.out.println("Film fundet indenfor genren: " + searchGenre + ":");
                            for (Movie foundGenreMovie : foundGenreMovies) {
                                System.out.println(foundGenreMovie.getTitle());
                            }
                        } else {
                            System.out.println("Ingen matchende film");
                        }
                        break;
                    default:
                        break;
                }
                break;


                    case 4:
                        System.out.println("Hvilken film skal redigeres?");
                        controller.displayMovieList();
                        System.out.println("Skriv navnet på film du vil omdøbe");
                        String editTitle = input.next();
                        Movie editMovie = controller.searchTitle(editTitle);

                        if (editMovie != null) {
                            System.out.println("Ny ønskede titel");
                            String newTitle = input.next();
                            editMovie.setTitle(newTitle);
                            System.out.println("Redigerer " + editTitle + " til: " + newTitle);
                        } else {
                            System.out.println("Film ikke fundet");
                        }
                        break;


                    case 5:
                        System.out.println("Hvilken film skal slettes?");
                        controller.displayMovieList();
                        System.out.println("Skriv navnet på film du vil slette");
                        String deleteTitle = input.next();
                        Movie deleteMovie = controller.searchTitle(deleteTitle);
                        if (deleteMovie != null) {
                            controller.removeMovie(deleteMovie);
                            System.out.println("Film slettet");
                        } else {
                            System.out.println("Film ikke fundet");
                        }
                        break;


                    case 6:
                        System.out.println("Nok filmdatabasering for i dag afslut");
                        running = false; // Stopper while-løkken og dermed programmet
                        break;

                    default:
                        System.out.println("Du SKAL skrive tal 1 eller 2. Prøv igen :)"); // Fejlbesked hvis brugeren ikke indtaster 1 eller 2
                        break;
                }
        }

    }



