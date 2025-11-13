


public class Movie {
    final private String title;
    final private int releaseYear;
    final private String genre;
    final private double rating;

    // constructor
    public Movie(String title, int releaseYear, String genre, double rating) {
        this.genre = genre;
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
    } // ends constructor

    @Override
    public String toString() {
        return title + " (" + releaseYear + ") – " + genre + " – " + rating;
    }

    // getter
    public String getTitle() {
        return title;
    } // ends getter

} // ends class
