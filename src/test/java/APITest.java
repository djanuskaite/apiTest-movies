import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class APITest {
    Movie movielocal;
    @Before
    public void setup() {
        movielocal = new Movie("Guardians of the Galaxy Vol. 2", 2017, "James Gunn", 7.6);
    }

    @Test
    public void apiTest() {
        try {
            JSONObject json = API.readJsonFromUrl("http://www.omdbapi.com/?i=tt3896198&apikey=3d842e99");
        Movie movieRemote = new Movie(json.getString("Title"), json.getInt("Year"), json.getString("Director"),
                json.getDouble("imdbRating"));
            assertMovieEquals(movielocal, movieRemote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void assertMovieEquals(Movie movielocal, Movie movieRemote){
        Assert.assertEquals(movielocal.getTitle(), movieRemote.getTitle());
        Assert.assertEquals(movielocal.getYear(), movieRemote.getYear());
        Assert.assertEquals(movielocal.getDirector(), movieRemote.getDirector());
        Assert.assertEquals(movielocal.getImdbRating(), movieRemote.getImdbRating(), 0);
    }
}
