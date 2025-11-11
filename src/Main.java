import utils.FileIO;         // import your FileIO class
import java.util.ArrayList;  // if you use ArrayList directly

public class Main {
    public static void main(String[] args){

        FileIO fileIO = new FileIO();
        ArrayList<String> lines = fileIO.readData("data/film.csv");

        ArrayList<Movie> movies = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(";");


            movies.add(new Movie(
                    parts[0].trim(),
                    Integer.parseInt(parts[1].trim()),
                    parts[2].trim(),
                    Double.parseDouble(parts[3].trim().replace(",", "."))
            ));
        }

        StreamingService netflix = new StreamingService(movies);

        netflix.printAllMovies();


        /* Code for later when implementing menus
        String name = "Anders";
        // Start menu
        System.out.println(
                ".........................\n" +
                ":        NETFLIX        :\n" +
                ":.......................:\n" +
                "      1. Log på\n" +
                "      2. Opret bruger");

        // Main menu
        System.out.println(
                ".........................\n" +
                ":   Velkommen, " + name + "   :\n" +
                ":.......................:\n" +
                "      1. Søg efter film\n" +
                "      2. Log af");
        */

    } // ends main method










} // ends main class
