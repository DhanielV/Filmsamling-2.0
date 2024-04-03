import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    Scanner input = new Scanner(System.in);

    Controller controller = new Controller(); // Opretter en ny controller og sender movieCollection som parameter
    private boolean running = true;

    public void startProgram() {
        System.out.println("Movie database:");

        while (running) {
            displayMenu();
            try {


                int choice = input.nextInt();
                processChoice(choice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Type a number");
                input.next();
            }
        }
    }

    private void displayMenu() {


        System.out.println("Menu");
        System.out.println("1. Add new movie to database");
        System.out.println("2. See movie list");
        System.out.println("3. Search for title, director or genre");
        System.out.println("4. Edit in movie");
        System.out.println("5. Remove movie");
        System.out.println("6. Exit");
        System.out.println("Type 1, 2, 3, 4, 5 or 6: ");
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1 -> addNewMovie();
            case 2 -> displayMovieList();
            case 3 -> searchMovies();
            case 4 -> editMovie();
            case 5 -> removeMovie();
            case 6 -> {
                System.out.println("Exiting");
                running = false; // Stopper while-løkken og dermed programmet
            }
            default -> {
            }
        }
    }

    private void addNewMovie() {
        //Nye film
        System.out.println("Type in title");
        String title = input.next();

        System.out.println("Type in director name");
        String director = input.next();

        int year = 0;
        boolean validYear = false;
        while (!validYear) {
            System.out.println("Type in year");
            if (input.hasNextInt()) {
                year = input.nextInt();
                validYear = true;
            } else {
                System.out.println("You must type a number try again");
                input.next();
            }
        }


        int movieMinutes = 0;
        boolean validLength = false;
        while (!validLength) {
            System.out.println("Type in length in minuttes");
            if (input.hasNextInt()) {
                movieMinutes = input.nextInt();
                validLength = true;
            } else {
                System.out.println("You must type a number try again");
                input.next();

            }
        }


        System.out.println("Genre");
        String genre = input.next();

        Movie movie = new Movie(title, director, year, movieMinutes, genre); // Opretter en ny film med de indtastede oplysninger

        controller.addMovie(movie); // Tilføjer den nye film til DB via controlleren
        System.out.println("Movie added: " + title + "\nDirector: " + director +
                "\nYear: " + year + "\nDuration: " + movieMinutes + " minuttes\nGenre: " + genre);

    }

    public void displayMovieList() {
        System.out.println("List of movies in database:\n");
        String displayMovieList = controller.displayMovieList();
        System.out.println(displayMovieList);
    }

    public void searchMovies() {
        System.out.println("Enter 1-3 to search for: \n 1. Title \n 2. Director \n 3. Genre ");
        int searchChoice = input.nextInt();
        switch (searchChoice) {
            case 1:
                System.out.println("Enter titel of movie");
                String searchTitle = input.next();
                Movie foundTitleMovie = controller.searchTitle(searchTitle);
                if (foundTitleMovie != null) {
                    System.out.println("Movie found: " + foundTitleMovie.getTitle());

                } else {
                    System.out.println("No matching movies");
                }
                break;
            case 2:
                System.out.println("Enter director name");

                String searchDirector = input.next();
                ArrayList<Movie> foundDirectorMovies = controller.searchDirector(searchDirector);
                if (foundDirectorMovies != null && !foundDirectorMovies.isEmpty()) {
                    System.out.println("Movie(s) found by: " + searchDirector + ":");
                    for (Movie foundDirectorMovie : foundDirectorMovies) {
                        System.out.println(foundDirectorMovie.getTitle());
                    }
                } else {
                    System.out.println("No matching movies");
                }
                break;


            case 3:
                System.out.println("Enter genre");
                String searchGenre = input.next();
                ArrayList<Movie> foundGenreMovies = controller.searchGenre(searchGenre);
                if (foundGenreMovies != null && !foundGenreMovies.isEmpty()) {
                    System.out.println("Movie(s) found with genre " + searchGenre + ":");
                    for (Movie foundGenreMovie : foundGenreMovies) {
                        System.out.println(foundGenreMovie.getTitle());
                    }
                } else {
                    System.out.println("No matching movies");
                }
                break;
            default:
                break;
        }

    }

    public void editMovie() {


        System.out.println("Enter name of movie you want to edit");
        String editTitle = input.next();

        Movie editMovie = controller.searchTitle(editTitle);

        if (editMovie != null) {
            System.out.println("Enter new title");
            String newTitle = input.next();
            editMovie.setTitle(newTitle);

            System.out.println("Enter new name of director");
            String newDirector = input.next();
            editMovie.setDirector(newDirector);


            int newYear = 0;
            boolean validNewYear = false;
            while (!validNewYear) {
                System.out.println("Enter new release year");
                if (input.hasNextInt()) {
                    newYear = input.nextInt();
                    validNewYear = true;
                } else {
                    System.out.println("You must type a number. Please try again.");
                    input.next();
                }
            }
            editMovie.setYear(newYear);

            int newMovieMinutes = 0;
            boolean validNewLength = false;
            while (!validNewLength) {
                System.out.println("Enter new movie length in minutes");
                if (input.hasNextInt()) {
                    newMovieMinutes = input.nextInt();
                    validNewLength = true;
                } else {
                    System.out.println("You must type a number. Please try again.");
                    input.next();
                }
            }
            editMovie.setMovieMinutes(newMovieMinutes);

            System.out.println("Enter new name of Genre");
            String newGenre = input.next();
            editMovie.setGenre(newGenre);

            System.out.println("Movie updated successfully.");
        } else {
            System.out.println("Movie not found");
        }

    }

    public void removeMovie() {
        System.out.println("Enter name of movie you want to delete");
        String deleteTitle = input.next();
        Movie deleteMovie = controller.searchTitle(deleteTitle);
        if (deleteMovie != null) {
            controller.removeMovie(deleteMovie);
            System.out.println("Movie deleted");
        } else {
            System.out.println("Movie not found");
        }

    }


}



