package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
    @Mock
    CrdDao dao;

    @InjectMocks
    TwitterService service;

    @Test
    public void postTweet() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        when(dao.create(any())).thenReturn(new Tweet());
        Tweet tweet = new Tweet();
        tweet.setText("september");
        service.postTweet(tweet);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());

        Tweet longTweet = new Tweet();
        longTweet.setText("september september september september september september september september september september september september september september september september");
        try {
            service.postTweet(longTweet);
            fail();
        } catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void showTweet() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        when(dao.findById(any())).thenReturn(new Tweet());
        String[] fields = {"created_at", "id", "id_str", "text", "entities", "coordinates", "retweet_count", "favorite_count", "favorited", "retweeted"};
        Tweet tweet = service.showTweet("123", fields);
        assertNotNull(tweet);

        String[] incorrectFields = {"created_at", "texts"};
        String incorrectID = "nariman";
        try {
            service.showTweet(incorrectID, incorrectFields);
            fail();
        } catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void deleteTweets() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        when(dao.deleteById(any())).thenReturn(new Tweet());
        String[] ids = {"1", "2", "3"};
        List<Tweet> tweets = service.deleteTweets(ids);
        assertNotNull(tweets);

        String[] incorrectIDs = {"1", "2", "a"};
        try {
            service.deleteTweets(incorrectIDs);
            fail();
        } catch (Exception e){
            assertTrue(true);
        }
    }
}