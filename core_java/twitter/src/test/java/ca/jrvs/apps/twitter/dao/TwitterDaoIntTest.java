package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {

    private final static String CONSUMER_KEY = System.getenv("consumerKey");
    private final static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private final static String ACCESS_TOKEN = System.getenv("accessToken");
    private final static String TOKEN_SECRET = System.getenv("tokenSecret");
    private TwitterDao dao;

    @Before
    public void setUp() throws Exception {
        TwitterHttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        dao = new TwitterDao(httpHelper);
    }

    @Test
    public void create() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        // Important to change sample text every run, as Twitter doesn't allow for the same tweet to be posted twice
        // Percent escaper is needed for multi-word tweets
        String sampleText = "sep2016";
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        String escapedText = percentEscaper.escape(sampleText);
        // Longitude and latitude must be between -90 and +90, otherwise will be ignored
        Double longitude = 30.1;
        Double latitude = 30.2;
        List<Double> coordList = new ArrayList<>();
        coordList.add(longitude);
        coordList.add(latitude);

        Tweet tweet = new Tweet();
        Coordinates coords = new Coordinates();
        coords.setCoordinates(coordList);
        tweet.setCoordinates(coords);
        tweet.setText(escapedText);

        dao.create(tweet);

        // Assert the properties we set match
        assertEquals(longitude, tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(latitude, tweet.getCoordinates().getCoordinates().get(1));
        assertEquals(escapedText, tweet.getText());
    }

    @Test
    public void findById() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        // A sample tweet that I posted
        String sampleID = "1383521808712822790";
        String sampleText = "sep2s016";
        Double expectedLongitude = 30.1;
        Double expectedLatitude = 30.2;

        Tweet tweet = dao.findById(sampleID);

        // Assert the tweet we created above is fetched with the correct information
        assertEquals(tweet.getText(), sampleText);
        assertEquals(tweet.getCoordinates().getCoordinates().get(0), expectedLongitude);
        assertEquals(tweet.getCoordinates().getCoordinates().get(1), expectedLatitude);
    }

    @Test
    public void deleteById() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        // Testing deletion on the above created tweet
        String sampleText = "sep2s016";
        String sampleID = "1383521808712822790";

        // Simply check to make sure the tweet exists before deletion and doesn't exist after deletion
        assertNotNull(dao.findById(sampleID));
        Tweet tweet = dao.deleteById(sampleID);
        assertEquals(tweet.getId().toString(), sampleID);
        assertEquals(tweet.getText(), sampleText);
    }
}