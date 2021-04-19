package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import net.minidev.json.JSONUtil;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    static String tweetJSON = "{"
            + " \"created_at\":\"Fri Apr 16 10:02:00 2021\",\n"
            + " \"id\":123,\n"
            + " \"id_str\":\"123\",\n"
            + " \"text\":\"sep2016\","
            + " \"entities\":{\n"
            + "     \"hashtags\":[],"
            + "     \"user_mentions\":[]"
            + " },\n"
            + " \"coordinates\":null,\n"
            + " \"retweet_count\":0,\n"
            + " \"favorite_count\":0,\n"
            + " \"favorited\":false,\n"
            + " \"retweeted\":false\n"
            + "}";

    @Test
    public void create() throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException, URISyntaxException {
        String sampleText = "sep2016";
        Tweet tweet = new Tweet();
        tweet.setText(sampleText);

        try {
            dao.create(tweet);
            fail();
        } catch (RuntimeException | URISyntaxException e){
            assertTrue(true);
        }

        TwitterDao spyDAO = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJSON, Tweet.class);
        doReturn(expectedTweet).when(spyDAO).parseResponse(any());

        Tweet spyTweet = spyDAO.create(tweet);
        assertNotNull(spyTweet);
        assertNotNull(spyTweet.getText());
    }

    @Test
    public void findById() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String sampleID = "1383521808712822790";
        String sampleText = "sep2016";

        try {
            dao.findById(sampleID);
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        TwitterDao spyDAO = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJSON, Tweet.class);
        doReturn(expectedTweet).when(spyDAO).parseResponse(any());

        Tweet spyTweet = spyDAO.findById(sampleID);
        assertNotNull(spyTweet);
        assertNotNull(spyTweet.getText());
    }

    @Test
    public void deleteById() throws IOException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, URISyntaxException {
        String sampleID = "1383521808712822790";
        String sampleText = "sep2016";

        try {
            dao.deleteById(sampleID);
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        TwitterDao spyDAO = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJSON, Tweet.class);
        doReturn(expectedTweet).when(spyDAO).parseResponse(any());

        Tweet spyTweet = spyDAO.deleteById(sampleID);
        assertNotNull(spyTweet);
        assertNotNull(spyTweet.getText());
    }
}