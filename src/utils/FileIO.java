package utils;

import java.io.*;
import java.util.*;

public class FileIO {

    public ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(path))) {
            while (scan.hasNextLine()) data.add(scan.nextLine().trim());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
        return data;
    }

    public void saveData(ArrayList<String> list, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            for (String s : list) writer.write(s + "\n");
        } catch (IOException e) {
            System.out.println("Problem: " + e.getMessage());
        }
    }
}