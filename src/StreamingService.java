import java.util.ArrayList;

import utils.FileIO;
import utils.TextUI;

public class StreamingService {
    private User currentUser;
    private ArrayList<Movie> movies;
    private ArrayList<String> users;
    private FileIO fileIO;
    private ArrayList<String> watchlist = new ArrayList<>();

    public StreamingService(ArrayList<Movie> movies, ArrayList<String> users, FileIO fileIO) {
        this.movies = movies;
        this.users = users;
        this.fileIO = fileIO;
    }

    // metode til at gøre watchlisten pæn. 100% AI-genereret
    private void printWatchlist() {
        String username = (currentUser != null) ? currentUser.getUserName() : "User";

        final int BOX_WIDTH = 44;          // total width INCLUDING the two borders
        final int INNER_WIDTH = BOX_WIDTH - 2; // width between the two borders
        final int INDEX_WIDTH = 4;         // e.g. "  1."
        final int SPACE_AFTER_INDEX = 1;   // space after "  1."
        final int TITLE_WIDTH = INNER_WIDTH - INDEX_WIDTH - SPACE_AFTER_INDEX;

        // Top border
        String horizontal = "─".repeat(INNER_WIDTH);
        System.out.println("┌" + horizontal + "┐");

        // Header: "<username>'s WATCHLIST", centered
        String header = username + "'s WATCHLIST";
        int padLeft = (INNER_WIDTH - header.length()) / 2;
        int padRight = INNER_WIDTH - padLeft - header.length();
        String headerLine = " ".repeat(Math.max(0, padLeft)) +
                header +
                " ".repeat(Math.max(0, padRight));
        System.out.println("│" + headerLine + "│");

        // Separator
        System.out.println("├" + horizontal + "┤");

        // Entries
        for (int i = 0; i < watchlist.size(); i++) {
            String title = watchlist.get(i);
            String displayTitle = title;

            // Truncate if too long
            if (displayTitle.length() > TITLE_WIDTH) {
                displayTitle = displayTitle.substring(0, TITLE_WIDTH - 3) + "...";
            }

            String indexPart = String.format("%2d.", i + 1); // " 1." / "10." etc.
            String content = indexPart + " " + displayTitle;

            // Pad with spaces so the line fills INNER_WIDTH exactly
            if (content.length() < INNER_WIDTH) {
                content = content + " ".repeat(INNER_WIDTH - content.length());
            }

            System.out.println("│" + content + "│");
        }

        // Bottom border
        System.out.println("└" + horizontal + "┘");
    } // ends printWatchlist

    public void printAllMovies() {
        for (Movie m : movies) {
            System.out.println(m);
        }
    }

        // START MENU
    public void showStartMenu(TextUI ui) {
        System.out.println("┌─────────────────────────┐");
        System.out.println("│        NETFLIX          │");
        System.out.println("│       START MENU        │");
        System.out.println("├─────────────────────────┤");
        System.out.println("│  1. Log in              │");
        System.out.println("│  2. Create account      │");
        System.out.println("└─────────────────────────┘");

        int startMenuChoice = ui.promptInt("");

        if (startMenuChoice == 1) {
            String typedUserName = ui.promptText("Enter your user name: ");
            if (users.contains(typedUserName)) {
                currentUser = new User(typedUserName);
                watchlist = fileIO.readWatchlist(currentUser.getUserName());
                System.out.println("Welcome, " + currentUser.getUserName());
            } else {
                System.out.println("Account not found. Try again, or create a new account.");
                showStartMenu(ui);
                return;
            }
        } else if (startMenuChoice == 2) {
            String typedName = ui.promptText("Type your name.\n");
            if (users.contains(typedName)) {
                System.out.println("That name already exists. Try logging in instead.");
                showStartMenu(ui);
                return;
            }
            currentUser = new User(typedName);
            users.add(typedName);
            fileIO.saveUsers("data/users.csv", users);
            watchlist = fileIO.readWatchlist(currentUser.getUserName()); // laver en tom watchlist
            System.out.println("Account created. Welcome, " + currentUser.getUserName() + ".");
        }
        if (currentUser != null) showMainMenu(ui); // sikrer, at login kun sker, hvis en bruger er logget på
    } // ends start menu

    // MAIN MENU
    public void showMainMenu(TextUI ui) {
        while (true) {
            System.out.println("┌─────────────────────────┐");
            System.out.println("│        NETFLIX          │");
            System.out.println("│       MAIN MENU         │");
            System.out.println("├─────────────────────────┤");
            System.out.println("│  1. Search movie        │");
            System.out.println("│  2. View watchlist      │");
            System.out.println("│  3. Log out             │");
            System.out.println("└─────────────────────────┘");

            int choice = ui.promptInt("");

            if (choice == 1) {
                while (true) {
                    String searchChoice = ui.promptText("Type the movie name (or type 'back' to return): ");
                    if (searchChoice.equalsIgnoreCase("back")) break;

                    boolean found = false;
                    String q = searchChoice.toLowerCase(); // konverterer blot brugerens søgning til små bogstaver
                    for (Movie m : movies) {
                        if (m.getTitle().toLowerCase().contains(q)) {
                            System.out.println(m.getTitle() + " found.");
                            int subChoice = ui.promptInt("1. Play movie\n2. Add to watchlist\nChoose: ");

                            if (subChoice == 1) {
                                System.out.println(m.getTitle() + " is now playing...");
                            } else if (subChoice == 2) {
                                if (!watchlist.contains(m.getTitle())) {
                                    watchlist.add(m.getTitle());
                                    fileIO.saveWatchlist(currentUser.getUserName(), watchlist);
                                    System.out.println("'" + m.getTitle() + "' added to your watchlist.");
                                } else {
                                    System.out.println("Already in your watchlist.");
                                }
                            } else {
                                System.out.println("Invalid choice.");
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("The movie '" + searchChoice + "' does not exist.\nTry another title.");
                    } else break;
                }
            } else if (choice == 2) {
                if (watchlist.isEmpty()) {
                    System.out.println("Your watchlist is empty.");
                } else {
                    printWatchlist();
                }
            } else if (choice == 3) {
                currentUser = null;
                System.out.println("Logged out. Returning to Start Menu.");
                showStartMenu(ui);
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        // metode til at gøre watchlisten flottere


    } // ends main menu
} // ends class