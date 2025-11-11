import java.util.ArrayList;

public class StreamingService {

    private ArrayList<Movie> movies;

    public StreamingService(ArrayList<Movie> movies) {
        this.movies = movies;
    }


    public void printAllMovies(){
        for (Movie m : movies) {
            System.out.println(m);
        }
    }

}
