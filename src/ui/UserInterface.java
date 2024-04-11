package ui;

import controller.Controller;
import domainmodel.Movie;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class UserInterface {

    Scanner input = new Scanner(System.in);

    Controller controller = new Controller(); // Opretter en ny controller og sender movieCollection som parameter
    private boolean running = true;

    public void startProgram() {
        loadMoviesOnStart();
        System.out.println("Movie database:");

        while (running) {
            displayMenu();
            try {


                int choice = input.nextInt();
                processMainMenuChoice(choice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Type a number");
                input.next();
            }
        }
    }

    private void loadMoviesOnStart() {
        controller.loadMovies();
    }

    private void displayMenu() {


        System.out.println("""
                Menu\s
                1. Add new movie to database\s
                2. See movie list\s
                3. Search for title, director or genre\s
                4. Edit in movie\s
                5. Remove movie\s
                6. Exit and save\s
                Type 1, 2, 3, 4, 5 or 6:\s""");
    }

    public void exit() {
        System.out.println("Exiting...");
        running = false; // Stopper while-løkken og dermed programmet

    }

    private void exitSave() {
        System.out.println("Saving...");
        controller.saveMovies();
        exit();
    }

    private void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> addNewMovie();
            case 2 -> sortOptions();
            case 3 -> searchMovies();
            case 4 -> editMovie();
            case 5 -> removeMovie();
            case 6 -> exitSave();


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

    public void sortOptions() {

        System.out.println("""
                How would you like to sort the movie list?\s
                 1. Don't sort\s
                 2. Sort title alphabetically
                 3. Sort director alphabetically
                 4. Sort by movie minutes in ascending order
                 5. Sort by year in ascending order
                 6. Sort by genre alphabetically""");


        int sortChoice;
        boolean validSortChoice = false;
        while (!validSortChoice) {
            if (input.hasNextInt()){
                sortChoice = input.nextInt();
                validSortChoice = true;

            switch (sortChoice) {
                case 1:
                    displayMovieListSort("unsorted");
                    break;
                case 2:
                    displayMovieListSort("title");
                    break;
                case 3:
                    displayMovieListSort("director");
                    break;
                case 4:
                    displayMovieListSort("movieMinutes");
                    break;
                case 5:
                    displayMovieListSort("year");
                    break;
                case 6:
                    displayMovieListSort("genre");
                    break;
                default:
                    break;
            }

            }else
                System.out.println("You must enter a number");
            input.nextLine();
        }
    }


    public void displayMovieListSort(String sortBy){
        String message;
        switch (sortBy){
            case "unsorted":
                message = "List of movies unsorted";
                System.out.println(message + controller.displayMovieList());
                break;

            case "title":
                message = "List of movies sorted alphabetically by title:\n";
                System.out.println(message + controller.displayMovieListSortTitleAlphabetically());
                break;
            case "director":
                message = "Movie list sorted alphabetically by director: \n ";
                System.out.println(message + controller.displayMovieListSortDirectorAlphabetically());
                break;
            case "movieMinutes":
                message = "Movie list sorted by movie minutes in ascending order: \n";
                System.out.println(message + controller.displayMovieListSortMovieMinutesAscending());
                break;
            case "year":
                message = "Movie list sorted by year in ascending order: \n";
                System.out.println(message + controller.displayMovieListSortYearAscending());
                break;
            case "genre":
                message = "Movie list sorted alphabetically by genre: \n ";
                System.out.println(message + controller.displayMovieListSortGenreAlphabetically());
                break;
            default:
                break;
        }
    }

    public void searchMovies() {
        System.out.println("Enter 1-3 to search for: \n 1. Title \n 2. Director \n 3. Genre ");
        int searchChoice = input.nextInt();
        input.nextLine();
        switch (searchChoice) {
            case 1:
                System.out.println("Enter title of movie");
                String searchTitle = input.nextLine();
                Movie foundTitleMovie = controller.searchTitle(searchTitle);
                if (foundTitleMovie != null) {
                    System.out.println("Movie found: " + foundTitleMovie.getTitle());

                } else {
                    System.out.println("No matching movies");
                }
                break;
            case 2:
                System.out.println("Enter director name");

                String searchDirector = input.nextLine();
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
                String searchGenre = input.nextLine();
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

            controller.movieEdited();

            controller.saveMovies();

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



