import utils.FileIO;
import utils.TextUI;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // film og brugere indlæses i string arraylister
        FileIO fileIO = new FileIO();
        ArrayList<String> lines = fileIO.readData("data/film.csv");
        ArrayList<String> users = fileIO.readUsers("data/users.csv");
        ArrayList<Movie> movies = new ArrayList<>();


        for (String line : lines) {
            if (line.isBlank()) continue; // sikkerhed: Hvis en linje er tom, springes den over

            String[] parts = line.split(";"); // opdeler hver streng (dvs. linje) i dele (array) adskilt af semikolon
            if (parts.length < 4) continue;        // sikkerhed: Hvis en linje har færre dele end 4, springes den over

            movies.add(new Movie(
                    parts[0].trim(), // titel, "trim" fjerner løse mellemrum
                    Integer.parseInt(parts[1].trim()), // årstal, streng konverteres til int
                    parts[2].trim(), // genre
                    Double.parseDouble(parts[3].trim().replace(",", ".")) // rating,
                    // streng konverteres til double, "," erstattes med ".", da Java ikke kan håndtere komma
            ));
        }

        // instantierer en streamingtjeneste og viser startmenuen
        TextUI ui = new TextUI();
        StreamingService netflix = new StreamingService(movies, users, fileIO);

        netflix.showStartMenu(ui);

    } // ends main method
} // ends Main class