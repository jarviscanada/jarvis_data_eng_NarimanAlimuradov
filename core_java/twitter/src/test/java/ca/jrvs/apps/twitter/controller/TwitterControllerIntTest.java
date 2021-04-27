package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterControllerIntTest {

    private final static String CONSUMER_KEY = System.getenv("consumerKey");
    private final static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private final static String ACCESS_TOKEN = System.getenv("accessToken");
    private final static String TOKEN_SECRET = System.getenv("tokenSecret");

    private TwitterDao dao;
    private TwitterService service;
    private TwitterController controller;

    @Before
    public void setUp() throws Exception {
        TwitterHttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        dao = new TwitterDao(httpHelper);
        service = new TwitterService(dao);
        controller = new TwitterController(service);
    }

    @Test
    public void postTweet() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String[] args = {"October", "30.0:30.1"};
        Tweet tweet = controller.postTweet(args);
        assertNotNull(tweet);
        assertEquals(tweet.getText(), args[0]);
    }

    @Test
    public void showTweet() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String[] args = {"1382768599908110344", "created_at", "id", "id_str", "text"};
        String sampleText = "API TEST 3 API TEST 3";
        Tweet tweet = controller.showTweet(args);
        assertEquals(tweet.getText(), sampleText);
        assertEquals(tweet.getId_str(), args[0]);
    }

    @Test
    public void deleteTweet() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String[] args = {"1384199594800672775,1384197832756793351"};
        String[] sampleText = {"September", "October"};
        List<Tweet> tweets = controller.deleteTweet(args);
        assertEquals(sampleText[0], tweets.get(0).getText());
        assertEquals(sampleText[1], tweets.get(1).getText());
    }
}