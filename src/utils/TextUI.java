package utils;
import java.util.Scanner;

public class TextUI {

    private final Scanner sc = new Scanner(System.in); // nyt objekt, der læser fra tastatur

    // læser tekst fra brugeren
    public String promptText(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    // læser et tal fra brugeren
    public int promptInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }
} // ends class