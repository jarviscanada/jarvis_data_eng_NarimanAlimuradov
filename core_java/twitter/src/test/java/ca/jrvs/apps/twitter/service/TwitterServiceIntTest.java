package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
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

public class TwitterServiceIntTest {

    private final static String CONSUMER_KEY = System.getenv("consumerKey");
    private final static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private final static String ACCESS_TOKEN = System.getenv("accessToken");
    private final static String TOKEN_SECRET = System.getenv("tokenSecret");
    private TwitterDao dao;
    private TwitterService service;

    @Before
    public void setUp() throws Exception {
        TwitterHttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        dao = new TwitterDao(httpHelper);
        service = new TwitterService(dao);
    }

    @Test
    public void postTweet() throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        String sampleText = "september0";
        Double longitude = 160.5;
        Double latitude = 15.5;
        List<Double> coordList = new ArrayList<Double>();
        coordList.add(longitude);
        coordList.add(latitude);
        Coordinates coords = new Coordinates();
        coords.setCoordinates(coordList);

        Tweet tweet = new Tweet();
        tweet.setText(sampleText);
        tweet.setCoordinates(coords);
        Tweet postTweet = service.postTweet(tweet);

        assertEquals(postTweet.getText(), sampleText);
        assertEquals(postTweet.getCoordinates().getCoordinates().get(0), longitude);
        assertEquals(postTweet.getCoordinates().getCoordinates().get(1), latitude);
    }

    @Test
    public void showTweet() throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        String sampleID = "1383170143665192961";
        String sampleText = "API DELETE TEST";
        String[] sampleFields = {"created_at", "id", "id_str", "text"};

        Tweet tweet = service.showTweet(sampleID, sampleFields);

        assertNotNull(tweet);
        assertEquals(tweet.getText(), sampleText);
        assertEquals(tweet.getId_str(), sampleID);
        assertNull(tweet.getEntities());
    }

    @Test
    public void deleteTweets() throws URISyntaxException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        // The IDs and text content of two tweets to be deleted. Must exist before running this.
        String[] sampleIDs = {"1384139494102769675", "1384139502990467083"};
        String[] sampleTexts = {"One", "Two"};
        List<Tweet> tweets = service.deleteTweets(sampleIDs);

        assertEquals(tweets.get(0).getText(), sampleTexts[0]);
        assertEquals(tweets.get(1).getText(), sampleTexts[1]);
    }
}