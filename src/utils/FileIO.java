package utils;

import java.io.*;
import java.util.*;

public class FileIO {

    // metode til at indlæse data (film og brugere) fra en fil med en liste af strenge som returtype
    // path bruges i Main
    public ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList<>(); // instantiering
        try (Scanner scan = new Scanner(new File(path))) {
            while (scan.hasNextLine()) data.add(scan.nextLine().trim());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
        return data;
    }

    // metode til at gemme data til en fil (brugere)
    public void saveData(ArrayList<String> list, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            for (String s : list) writer.write(s + "\n");
        } catch (IOException e) {
            System.out.println("Problem: " + e.getMessage());
        }
    }

    public ArrayList<String> readUsers(String filename) {
        return readData(filename);
    }

    public void saveUsers(String filename, ArrayList<String> users) {
        saveData(users, filename);
    }

            // læser watchlist
            public ArrayList<String> readWatchlist(String username) {
                ArrayList<String> list = new ArrayList<>();
                File f = new File("data/watchlists/" + username + ".csv");
                try (Scanner scan = new Scanner(f)) {
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine().trim();
                        if (!line.isEmpty()) list.add(line);
                    }
                } catch (FileNotFoundException ignored) {} // no file yet = empty list
                return list;
            }

            // gemmer watchlist
    public void saveWatchlist(String username, ArrayList<String> list) {
        File dir = new File("data/watchlists");
        if (!dir.exists()) dir.mkdirs();
        try (PrintWriter out = new PrintWriter(new FileWriter(new File(dir, username + ".csv")))) {
            for (String title : list) out.println(title);
        } catch (IOException e) {
            System.out.println("Error saving watchlist: " + e.getMessage());
        }
    }

} // ends class